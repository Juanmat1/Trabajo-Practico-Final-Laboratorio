package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Delta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class adminSelectionController {

    @FXML
    private Button buyButton;

    @FXML
    private Button userButton;

    @FXML
    private Button productosButton;

    @FXML
    private Button closeButton;
    @FXML
    private Button clientesButton;

    @FXML
    public void buyAction()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/CRUD_admin-importador.fxml"));
            Stage stage = (Stage) buyButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }
    @FXML
    public void userAction()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/CRUD_Usuarios.fxml"));
            Stage stage = (Stage) userButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }


    }

    @FXML
    public void productosAction()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/ADMIN_ProductosCrud.fxml"));
            Stage stage = (Stage) productosButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }


    }

    public void closeButton()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/LOGIN_Importadora.fxml"));
            Stage stage = (Stage) closeButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void clientesButton()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/CRUD_clientes.fxml"));
            Stage stage = (Stage) clientesButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }
    public void proveedoresAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/CRUD_Proveedores.fxml"));
            Stage stage = (Stage) productosButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

}
