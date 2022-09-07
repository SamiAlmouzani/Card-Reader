package com.example.cardreader;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class StartupController {
    @FXML
    private TextField tfUserId;
    @FXML
    private Label lblInvalidUser;
    @FXML
    TableView<TimeStamp> timeStampTableView;
    @FXML
    TableColumn<TimeStamp, String> colUser;
    @FXML
    TableColumn<TimeStamp, String> colStatus;
    @FXML
    TableColumn<TimeStamp, String> colRole;
    @FXML
    TableColumn<TimeStamp, String> colTimeIn;
    @FXML
    TableColumn<TimeStamp, String> colTimeOut;

    ObservableList<TimeStamp> data = FXCollections.observableArrayList();
    private MySQLAccess db = new MySQLAccess();

    private Stage stage;
    private Scene scene;
    private Parent root;


    public StartupController() throws Exception {
    }

    @FXML
    protected void onEnterButtonClick(ActionEvent event) throws Exception {
        Integer id = Integer.parseInt(tfUserId.getText());

        String role = db.validateUserId(id);

        Timestamp ts = new Timestamp(System.currentTimeMillis());

        db.addTimeStamp(id, ts);
        if(role == null){
            lblInvalidUser.setText("Password is incorrect.");
        }
        else if(role.compareTo("student") == 0){
            lblInvalidUser.setText("This is student");
        }else if(role.compareTo("faculty") == 0){
            lblInvalidUser.setText("This is faculty");

            Parent root = FXMLLoader.load(getClass().getResource("studentViewer.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            // execute query
            data = db.retrieveData(data);
            timeStampTableView.getColumns().clear();
            colUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            colTimeIn.setCellValueFactory(new PropertyValueFactory<>("time_in"));
            colTimeOut.setCellValueFactory(new PropertyValueFactory<>("time_out"));
            colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            timeStampTableView.setItems(data);
            timeStampTableView.getColumns().addAll(colUser, colTimeIn, colTimeOut, colRole, colStatus);

        }
    }
}
