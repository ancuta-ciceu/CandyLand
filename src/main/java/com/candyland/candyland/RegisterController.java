package com.candyland.candyland;
import javafx.application.Platform;
import  javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.Statement;


public class RegisterController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Label registerLabel;
    @FXML
    private ImageView CottonCandyView;
    @FXML
    private PasswordField setPasswordTextField;
    @FXML
    private PasswordField confirmPasswordTextField;
    @FXML
    private Label wrongPasswordLabel;
    @FXML
    private ChoiceBox<String> chooseRole;
    @FXML
    private TextField FirstnameTextField;
    @FXML
    private TextField LastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label wrongUsernameLabel;

    public ArrayList<String> users = new ArrayList<String>();

    public void addUsers(String u){
        users.add(u);
    }

    public void registerButtonOnAction(ActionEvent event){
        if(setPasswordTextField.getText().equals(confirmPasswordTextField.getText())){
            wrongPasswordLabel.setText("");
            registerUser();
        }
        else {
            wrongPasswordLabel.setText("Parolele nu corespund!");

        }
    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File CottonCandyFile = new File("images/CottonCandy.jpeg");
        Image CottonCandyImage = new Image(CottonCandyFile.toURI().toString());
        CottonCandyView.setImage(CottonCandyImage);

        chooseRole.getItems().add("Client");
        chooseRole.getItems().add("Furnizor");

    }
    private  String encodePassword(String salt, String password) {
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

    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = FirstnameTextField.getText();
        String lastname = LastnameTextField.getText();
        String username = usernameTextField.getText();
        String role = chooseRole.getSelectionModel().getSelectedItem();
        String password = encodePassword(usernameTextField.getText(),setPasswordTextField.getText());

        //Iterator<String> it = users.iterator();
        //while (it.hasNext()){
           // String u = it.next();
            //if(usernameTextField.getText().equals(u.toString())){
               // wrongUsernameLabel.setText("Nu puteti alege acest nume de utilizator");
            //}else {
                String insertFields = "INSERT INTO user_account(firstname, lastname, username, role, password) VALUE ('";
                String insertValues = firstname + "','" + lastname + "','" + username + "','" + role + "','" + password + "')";
                String insertToRegister = insertFields + insertValues;
                addUsers(username);

                try {
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(insertToRegister);

                    registerLabel.setText("Contul dumneavoastra a fost inregistrat!");

                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();

                }
            }
       // }
   // }

}
