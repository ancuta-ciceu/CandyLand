package com.candyland.candyland;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


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
    @FXML
    private ChoiceBox<String> userRole;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/background.jpeg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        userRole.getItems().add("Client");
        userRole.getItems().add("Furnizor");
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

    public String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public void validateLogin() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1)FROM user_account WHERE username='" + usernameTextField.getText() +
                "' AND password=  '" + encodePassword(usernameTextField.getText(), enterPasswordField.getText()) + "'";
        try {
            Statement statement;
            statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    // loginMessageLabel.setText("Congratulations!");
                    handleLoginAction();
                } else {
                    loginMessageLabel.setText("Invalid Login.Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountFromOnAction() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
            Stage registerstage = new Stage();
            registerstage.initStyle(StageStyle.UNDECORATED);
            registerstage.setScene(new Scene(root, 381, 569));
            registerstage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void handleLoginAction() {

        try {

            if (userRole.getSelectionModel().getSelectedItem().equals("Client")) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPage.fxml")));
                Stage registerstage = new Stage();
                registerstage.initStyle(StageStyle.UNDECORATED);
                registerstage.setScene(new Scene(root, 383, 704));
                registerstage.show();
            } else {
                if (userRole.getSelectionModel().getSelectedItem().equals("Furnizor")) {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPage_f.fxml")));
                    Stage registerstage1 = new Stage();
                    registerstage1.initStyle(StageStyle.UNDECORATED);
                    registerstage1.setScene(new Scene(root, 421, 609));
                    registerstage1.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


}