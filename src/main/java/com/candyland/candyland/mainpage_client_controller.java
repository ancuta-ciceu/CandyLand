package com.candyland.candyland;

import com.candyland.candyland.mainpage.Mainpage_F_Controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainpage_client_controller implements Initializable {
        @FXML
        private ImageView FundalClient;
        @FXML
        private TableColumn<Produse, String> availableColumn;
        @FXML
        private TableColumn<Produse, String> nameColumn;
        @FXML
        private TableColumn<Produse, String> priceColumn;
        @FXML
        private TableColumn<Comenzi, String>produsComandat;
        @FXML
        private TableColumn<Comenzi, String>cantitate;

        @FXML
        private Button addToCartBtn;
        @FXML
        private Button sortBtn;
        @FXML
        private Button filterBtn;
        @FXML
        private TableView<Produse> table;
    @FXML
    private TableView<Comenzi> tableIstoric;
        @FXML
        private TextField DenumProd;
        @FXML
        private TextField Cantitate;
        @FXML
        private Button closeButton;

        public void closeButtonOnAction(ActionEvent event){
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
            Platform.exit();
        }

        Connection con;
        PreparedStatement pst;
        int myIndex;
        public void Connect() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/produse", "root", "cojocaru07");
            } catch (ClassNotFoundException ex) {

            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Connect();
            table();
            table_comenzi();
        }
        public void table()
        {
            Connect();
            ObservableList<Produse> produs = FXCollections.observableArrayList();
            try
            {
                pst = con.prepareStatement("select denumire_produs,cantitate,pret from produse");
                ResultSet rs = pst.executeQuery();
                {
                    while (rs.next())
                    {
                        Produse st = new Produse();
                        st.setName(rs.getString("denumire_produs"));
                        st.setCantitate(rs.getString("cantitate"));
                        st.setPret(rs.getString("pret"));

                        produs.add(st);
                    }
                }
                table.setItems(produs);
                nameColumn.setCellValueFactory(f -> f.getValue().nameProperty());
                availableColumn.setCellValueFactory(f -> f.getValue().cantitateProperty());
                priceColumn.setCellValueFactory(f -> f.getValue().pretProperty());


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
                        DenumProd.setText(table.getItems().get(myIndex).getName());
                        Cantitate.setText(table.getItems().get(myIndex).getCantitate());
                    }
                });
                return myRow;
            });

        }

    public void table_comenzi()
    {
        Connect();
        ObservableList<Comenzi> comanda = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select denumire_produs,cantitate from comanda");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Comenzi st = new Comenzi();
                    st.setName(rs.getString("denumire_produs"));
                    st.setCantitate(rs.getString("cantitate"));
//                    st.setPret(rs.getString("pret"));

                    comanda.add(st);
                }
            }
            tableIstoric.setItems(comanda);
            produsComandat.setCellValueFactory(f -> f.getValue().nameProperty());
            cantitate.setCellValueFactory(f -> f.getValue().cantitateProperty());
            //priceColumn.setCellValueFactory(f -> f.getValue().pretProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(mainpage_client_controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setRowFactory( tv -> {
            TableRow<Produse> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableIstoric.getSelectionModel().getSelectedIndex();
                    produsComandat.setText(tableIstoric.getItems().get(myIndex).getName());
                    cantitate.setText(tableIstoric.getItems().get(myIndex).getCantitate());
                }
            });
            return  myRow;
        });

    }





}

