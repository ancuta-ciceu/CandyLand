package com.candyland.candyland;

import com.candyland.candyland.DatabaseConnection;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    public loginController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/background.jpeg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        this.brandingImageView.setImage(brandingImage);
    }

    public void loginButtonOnAction(ActionEvent event) {
        if (!this.usernameTextField.getText().isBlank() && !this.enterPasswordField.getText().isBlank()) {
            this.validateLogin();
        } else {
            this.loginMessageLabel.setText("Va rugam introduceti numele de utilizator si parola");
        }

    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage)this.cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String var10000 = this.usernameTextField.getText();
        String verifyLogin = "SELECT count(1) FROM user account WHERE username= '" + var10000 + "' AND password  '" + this.enterPasswordField.getText() + " ' ";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    this.createAccountForm();
                } else {
                    this.loginMessageLabel.setText("Autentificare nereusita. Incercati din nou.");
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            var6.getCause();
        }

    }

    public void createAccountForm() {
        try {
            Parent root = (Parent)FXMLLoader.load((URL)Objects.requireNonNull(this.getClass().getResource("register.fxml")));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 381.0D, 569.0D));
            registerStage.show();
        } catch (Exception var3) {
            var3.printStackTrace();
            var3.getCause();
        }

    }
}
