package com.candyland.candyland;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

public class mainpage_client_controller {
    @FXML
    private ImageView FundalClient;
    @FXML
    public void initialize(){
        File fundalFile = new File("images/Macarons.jpg");
        Image fundalImage = new Image(fundalFile.toURI().toString());
        FundalClient.setImage(fundalImage);
    }
/*
    @FXML
    private void SwitchToCos throws IOException{
        Main.setRoot("CosCumparaturi.fxml");
    }

    private void SwitchToIstoric()throws IOException {
        Main.setRoot("IstoricComenzi.fxml");
    }
    */


}
