package com.example.cardreader;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private String URL = "jdbc:mysql://localhost:3306/sys";
    private String username = "root";
    private String password = "1234";
    public String validateUserId(int id) throws Exception {
        String role;
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(URL, username, password);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select role from User where idUser='" + id + "'");

            if(resultSet.next()){
                role = resultSet.getString(1);
            } else {
                role = null;
            }


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

        return role;
    }

    public void addTimeStamp(int id, Timestamp ts) throws SQLException, ClassNotFoundException {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(URL, username, password);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            statement.executeUpdate("insert into timestamps (time_in, idUser) values ('" + ts.toString() + "', '" + id + "')");

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    public ObservableList<TimeStamp> retrieveData(ObservableList<TimeStamp> data) throws SQLException, ClassNotFoundException {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(URL, username, password);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select usr.idUser, usr.name, ts.time_in, ts.time_out, usr.role, usr.status\n" +
                                                    "from\n" +
                                                    "User usr natural join timestamps ts");
            while(resultSet.next()){
                data.add(new TimeStamp(Integer.toString(resultSet.getInt(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), Integer.toString(resultSet.getInt(6))));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return data;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
