module com.tp.tp_final_lab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;


    opens com.tp.tp_final_lab3 to javafx.fxml;
    exports com.tp.tp_final_lab3;
    exports com.tp.tp_final_lab3.Models;
    exports com.tp.tp_final_lab3.Repository;
    exports com.tp.tp_final_lab3.controllers;
    opens com.tp.tp_final_lab3.controllers to javafx.fxml;
    exports com.tp.tp_final_lab3.SingletonClasses;
    opens com.tp.tp_final_lab3.SingletonClasses to javafx.fxml;
    exports com.tp.tp_final_lab3.Models.ApiCotizaciones;
}