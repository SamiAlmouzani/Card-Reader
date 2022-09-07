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
            resultSet = statement.executeQuery("select * from timestamps");
            while(resultSet.next()){
                data.add(new TimeStamp(Integer.toString(resultSet.getInt(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), Integer.toString(resultSet.getInt(5))));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return data;
    }
    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);
        }
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
