package com.tp.tp_final_lab3.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class verificarClienteController {

    @FXML
    private TextField dniTextField;

    @FXML
    private Button verificarButton;

    @FXML
    private Button volverButton;

    @FXML
    void verificar() {

    }

    @FXML
    void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_Venta.fxml"));
            Stage stage = (Stage) volverButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

}