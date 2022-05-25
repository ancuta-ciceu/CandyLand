package com.candyland.candyland;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;

public class Mainpage_F_Controller implements Initializable {

    @FXML
    private TableColumn<Produse, String> CantitateColomn;

    @FXML
    private TableColumn<Produse, String> DenumireColomn;

    @FXML
    private TableColumn<Produse, String> IDColomn;
    @FXML
    private TableColumn<Produse, String> PretColumn;

    @FXML
    private TableView<Produse> table;

    @FXML
    private TextField txtCantitate;

    @FXML
    private TextField txtDenumire;
    @FXML
    private TextField txtPret;
    @FXML
    private TextField txtid;
    @FXML
    private Button closebtn;

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    public void AddOnAction(ActionEvent event) {
        String denumire_produs, cantitate,pret;
        Connect();
        denumire_produs = txtDenumire.getText();
        cantitate = txtCantitate.getText();
        pret=txtPret.getText();
        try {
            pst = con.prepareStatement("insert into produse (denumire_produs, cantitate, pret) values ('','','')");
            pst.setString(1,denumire_produs);
            pst.setString(2,cantitate);
            pst.setString(3, pret);
            int status=pst.executeUpdate();

            if(status==1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Adaugare produse");

                alert.setHeaderText("Adaugare produse");
                alert.setContentText("Produs adaugat");
                alert.showAndWait();
                table();
                txtDenumire.setText("");
                txtCantitate.setText("");
                txtPret.setText("");

                txtDenumire.requestFocus();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("");
                alert.setContentText("Nu s-a putut face adaugarea");
                alert.showAndWait();

            }


        }catch (SQLException ex){
            Logger.getLogger(Mainpage_F_Controller.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void table()
    {
        Connect();
        ObservableList<Produse> produs = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select ID,denumire_produs,cantitate,pret from produse");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Produse st = new Produse();
                    st.setId(rs.getString("ID"));
                    st.setName(rs.getString("denumire_produs"));
                    st.setCantitate(rs.getString("cantitate"));
                    st.setPret(rs.getString("pret"));
                    produs.add(st);
                }
            }
            table.setItems(produs);
            IDColomn.setCellValueFactory(f -> f.getValue().idProperty());
            DenumireColomn.setCellValueFactory(f -> f.getValue().nameProperty());
            CantitateColomn.setCellValueFactory(f -> f.getValue().cantitateProperty());
            PretColumn.setCellValueFactory(f->f.getValue().pretProperty());

        }

        catch (SQLException ex)
        {
            Logger.getLogger(Mainpage_F_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setRowFactory( tv -> {
            TableRow<Produse> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtDenumire.setText(table.getItems().get(myIndex).getName());
                    txtCantitate.setText(table.getItems().get(myIndex).getCantitate());
                    txtPret.setText(table.getItems().get(myIndex).getPret());
                }
            });
            return myRow;
        });


    }

    @FXML
    void Delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();
        id=Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        try{
            pst = con.prepareStatement("delete from produse where id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Produse");
                alert.setHeaderText("Produse");
                alert.setContentText("Ati sters acest produs");
                alert.showAndWait();
                table();

        }catch (SQLException ex){
            Logger.getLogger(Mainpage_F_Controller.class.getName()).log(Level.SEVERE, null,ex);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connect();
            table();

    }

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database", "root", "root");
        } catch (ClassNotFoundException ex) {

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        }

}






