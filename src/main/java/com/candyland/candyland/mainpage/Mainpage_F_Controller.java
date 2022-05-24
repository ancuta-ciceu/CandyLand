package com.candyland.candyland.mainpage;

import com.candyland.candyland.Produse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mainpage_F_Controller implements Initializable {

    @FXML
    private TableColumn<Produse, String> CantitateColomn;

    @FXML
    private TableColumn<Produse, String> DenumireColomn;

    @FXML
    private TableColumn<Produse, String> IDColomn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Produse> table;

    @FXML
    private TextField txtCantitate;

    @FXML
    private TextField txtDenumire;

    @FXML
    void Add(ActionEvent event) {
        String nume_produs, cantitate;
        Connect();
        nume_produs = txtDenumire.getText();
        cantitate = txtCantitate.getText();
        try {
            pst = con.prepareStatement("insert into produse (denumire_produs, cantitate) values (?,?,?)");
            pst.setString(1,nume_produs);
            pst.setString(2,cantitate);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adaugare produse");

            alert.setHeaderText("Adaugare produse");
            alert.setContentText("Produs adaugat");

            alert.showAndWait();

            table();

            txtDenumire.setText("");
            txtCantitate.setText("");
            txtDenumire.requestFocus();
        }catch (SQLException ex){

        }
    }

    public void table()
    {
        Connect();
        ObservableList<Produse> produs = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select ID,denumire_podus,cantitate from produse");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Produse st = new Produse();
                    st.setId(rs.getString("ID"));
                    st.setName(rs.getString("denumire_produs"));
                    st.setCantitate(rs.getString("cantitate"));
                    produs.add(st);
                }
            }
            table.setItems(produs);
            IDColomn.setCellValueFactory(f -> f.getValue().idProperty());
            DenumireColomn.setCellValueFactory(f -> f.getValue().nameProperty());
            CantitateColomn.setCellValueFactory(f -> f.getValue().cantitateProperty());


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

                }
            });
            return myRow;
        });


    }

    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

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
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/produse", "root", "cojocaru07");
        } catch (ClassNotFoundException ex) {

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        }

}






