package com.candyland.candyland;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
public Connection databaseLink;

public Connection getConnection(){
    String databaseName = "candydatabase.";
    String databaseUser = "user_account";
    String databasePassword = "putyourpasswordhere";
    String url = "jdbc:mysql://localhost/"+databaseName;

    try{
        Class.forName("com.mysql.jdbc.Driver");
        databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

    }catch (Exception e){
        e.printStackTrace();
        e.getCause();
    }
    return databaseLink;
}
}
