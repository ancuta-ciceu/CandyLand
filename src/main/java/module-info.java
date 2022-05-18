module CandyLand {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens com.candyland.candyland;
    opens com.candyland.candyland.register;
}