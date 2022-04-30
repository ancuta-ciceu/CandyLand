package com.candyland.candyland;
import javafx.application.Platform;
import  javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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

    public void registerButtonOnAction(ActionEvent event){
        if(setPasswordTextField.getText().equals(confirmPasswordTextField.getText())){
            wrongPasswordLabel.setText("");
            registerLabel.setText("Contul dumneavoastra a fost inregistrat!");
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

}
