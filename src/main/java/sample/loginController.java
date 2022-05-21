package sample;

import com.candyland.candyland.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;

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
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import java.net.URL;

public class loginController implements Initializable {
    //public TextField usernameTextField;
    //public PasswordField enterPasswordField;
    //@FXML
    //private Button loginButton;
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

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user account WHERE username= '" + usernameTextField.getText() + "' AND password  '" + enterPasswordField.getText() + " ' ";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult;
            queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    //loginMessageLabel.setText("Felicitari!");
                    createAccountForm();

                } else {
                    loginMessageLabel.setText("Autentificare nereusita. Incercati din nou.");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
    public void createAccountForm (){
            try{
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
                Stage registerStage = new Stage();
                registerStage.initStyle(StageStyle.UNDECORATED);
                registerStage.setScene(new Scene(root, 381, 569));
                registerStage.show();
            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
    }
}