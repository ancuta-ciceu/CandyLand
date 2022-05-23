package com.candyland.candyland;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

public class mainpage_furnizor_controller {
    @FXML
    private ImageView FundalFurnizor;
    @FXML
    public void initialize(){
        File fundalFile = new File("images/Macarons.jpg");
        Image fundalImage = new Image(fundalFile.toURI().toString());
        FundalFurnizor.setImage(fundalImage);
    }
/*
    @FXML
    private void EditProductsOnAction() throws IOException{
        Main.setRoot("editProducts.fxml");
    }

    @FXML
    private void DeleteProductsOnAction() throws IOException{
        Main.setRoot("deleteProducts.fxml");
    }

    @FXML
    private void AddProductsOnAction() throws IOException{
        Main.setRoot("AddProducts.fxml");
    }
  */
}
