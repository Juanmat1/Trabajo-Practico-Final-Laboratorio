package com.tp.tp_final_lab3.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class adminSelectionProveedoresController {

    @FXML
    private Button agregarButton;

    @FXML
    private Button verModificarButton;

    @FXML
    private Button volverButton;

    @FXML
    void crearProveedor(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/PROVEEDOR_Creacion.fxml"));
            Stage stage = (Stage) agregarButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

    @FXML
    void verModificar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/CRUD_Proveedores.fxml"));
            Stage stage = (Stage) verModificarButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/ADMIN_Seleccion.fxml"));
            Stage stage = (Stage) volverButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

}
