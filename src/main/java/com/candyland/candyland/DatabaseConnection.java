package com.candyland.candyland;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
public Connection databaseLink;

    /*public static void changeScene(ActionEvent event , String fxmlFile, String title, String username, String role){
        Parent root = null;
            if(username != null && role != null){
                try {
                    FXMLLoader loader = new FXMLLoader(DatabaseConnection.class.getResource(fxmlFile));
                    root = loader.load();
                    Mainpage_F_Controller mainpage_f_controller = loader.getController();
                    Mainpage_F_Controller.setUserInformation(username,role);
                }catch (){

                }
            }
        try {
            FXMLLoader loader = new FXMLLoader(DatabaseConnection.class.getResource(fxmlFile));
            root = FXMLLoader.load(DatabaseConnection.class.getResource(fxmlFile));
        }catch (IOException e){
            e.printStackTrace();

        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }*/

public Connection getConnection(){
    String databaseName = "candydatabase";
    String databaseUser = "root";
    String databasePassword = "cojocaru07";
    String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName;

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

    }catch (Exception e){
        e.printStackTrace();
        e.getCause();
    }
    return databaseLink;
}
}
