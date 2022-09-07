module com.example.cardreader {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cardreader to javafx.fxml;
    exports com.example.cardreader;
}