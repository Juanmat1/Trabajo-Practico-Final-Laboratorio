package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Clientes;
import com.tp.tp_final_lab3.Models.Delta;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.SingletonClasses.SingletonClienteClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class verificarClienteController {
    private final String pathJsonClientes = "src/main/java/com/tp/tp_final_lab3/Archives/clientes.json";
    private ObservableList<Clientes> clientes = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJsonClientes, Clientes.class));
    @FXML
    private TextField dniTextField;

    @FXML
    private Button verificarButton;

    @FXML
    private Button volverButton;

    @FXML
    void verificar() {
        if(!dniTextField.getText().isEmpty()){
            Clientes cliente = new Clientes();
            cliente.setDni(dniTextField.getText());

            if(clientes.contains(cliente)){

                Clientes aux = clientes.get(clientes.indexOf(cliente));

                SingletonClienteClass.getInstancia().SetInfo(aux);


                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_Vender.fxml"));
                    Stage stage = (Stage) verificarButton.getScene().getWindow();
                    Scene scene = new Scene(loader.load());

                    scene.setFill(Color.TRANSPARENT);
                    Delta.dragScene(stage,scene);

                    stage.setScene(scene);
                }catch (IOException io) {
                    io.printStackTrace();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al verificar");
                alert.setContentText("El cliente no existe, debe crear uno nuevo");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_Venta.fxml"));
            Stage stage = (Stage) volverButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

}