package com.candyland.candyland;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Comenzi {
//    private final StringProperty ID;
    private final StringProperty denumire_produs;
    private final StringProperty cantitate;
//    private final StringProperty pret;


    public Comenzi()
    {
        //ID = new SimpleStringProperty(this, "ID");
        denumire_produs = new SimpleStringProperty(this, "denumire_produs");
        cantitate = new SimpleStringProperty(this, "cantitate");
        //pret = new SimpleStringProperty(this, "pret");

    }

//    public StringProperty idProperty() { return ID; }
//    public String getId() { return ID.get(); }
//    public void setId(String newId) { ID.set(newId); }

    public StringProperty nameProperty() { return denumire_produs; }
    public String getName() { return denumire_produs.get(); }
    public void setName(String newName) { denumire_produs.set(newName); }

    public StringProperty cantitateProperty() { return cantitate; }
    public String getCantitate() { return cantitate.get(); }
    public void setCantitate(String newCantitate) { cantitate.set(newCantitate); }

//    public StringProperty pretProperty() { return pret; }
//    public String getPret() { return pret.get(); }
//    public void setPret(String newPret) { pret.set(newPret); }

}
