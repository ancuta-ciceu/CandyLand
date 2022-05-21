package com.candyland.candyland;
import javafx.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.net.URL;

public class loginController implements Initializable {
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/background.jpeg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }


    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Va rugam introduceti numele de utilizator si parola");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private static ArrayList<String> userList = new ArrayList<>();
    public static ArrayList<String> getUser() throws ClassNotFoundException, SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection conn = connectNow.getConnection();
        Statement stm1;
        stm1 = conn.createStatement();
        String sql = "Select usermane From user_account";
        ResultSet rst1;
        rst1 = stm1.executeQuery(sql);
        while (rst1.next()) {
            String username = rst1.getString("username");
            userList.add(username);
        }
        return userList;
    }

    private static ArrayList<String> passwordList = new ArrayList<>();
    public static ArrayList<String> getPassword() throws ClassNotFoundException, SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection conn = connectNow.getConnection();
        Statement stm2;
        stm2 = conn.createStatement();
        String sql = "Select password From user_account";
        ResultSet rst2;
        rst2 = stm2.executeQuery(sql);
        while (rst2.next()) {
            String password = rst2.getString("password");
            passwordList.add(password);
        }
        return passwordList;
    }

    public void validateLogin() {
        Iterator<String> it1 = userList.iterator();
        Iterator<String> it2 = passwordList.iterator();
        while (it1.hasNext() && it2.hasNext()){
            String s1 = it1.next();
            String s2 = it2.next();
            if(usernameTextField.getText().equals(s1.toString()) && enterPasswordField.getText().equals(s2.toString())){
                loginMessageLabel.setText("Felicitari!");
            }
            else{
                loginMessageLabel.setText("Autentificare nereusita");

            }
        }

    }
    public void createAccountFromOnAction(){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
            Stage registerstage = new Stage();
            registerstage.initStyle(StageStyle.UNDECORATED);
            registerstage.setScene(new Scene(root, 381, 569));
            registerstage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}