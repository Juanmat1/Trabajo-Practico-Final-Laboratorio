package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Delta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class usuarioSelectionController {
    @FXML
    private Text text;
    @FXML
    private Button importadoraButton;
    @FXML
    private Button ventasButton;
    @FXML
    private Button cerrarSesionButton;

    public void importadora(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/CRUD_Importador.fxml"));
            Stage stage = (Stage) importadoraButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }
    public void ventas(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_Venta.fxml"));
            Stage stage = (Stage) ventasButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    public void cerrarSesion(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/LOGIN_Importadora.fxml"));
            Stage stage = (Stage) cerrarSesionButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }
}
