package com.tp.tp_final_lab3.controllers;
import com.tp.tp_final_lab3.Models.Clientes;
import com.tp.tp_final_lab3.Models.EstadosPersona;
import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class createClientController implements Initializable {
@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    ArrayList<Clientes> clientes;

    @FXML
    private PasswordField textDomicilio;

    @FXML
    private PasswordField textCategoria;

    @FXML
    private AnchorPane creaCliente;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textApellido;

    @FXML
    private Button irAtrasButton;

    @FXML
    private Button createButton;

    @FXML
    private PasswordField textTelefono;

    @FXML
    private TextField textDNI;

    @FXML
    private TextField textCUIT;

    @FXML
    public void crearCliente() {

        clientes = Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/clientes.json", Clientes.class);
        Clientes clientes1 = new Clientes(textNombre.getText(),textApellido.getText(),textDNI.getText(),textCUIT.getText(),textDomicilio.getText(),textTelefono.getText(),textCategoria.getText(), EstadosPersona.Activo);

        if(textCUIT.getText().isEmpty() || textDomicilio.getText().isEmpty() || textTelefono.getText().isEmpty()
                || textNombre.getText().isEmpty() || textApellido.getText().isEmpty() || textDNI.getText().isEmpty() || textCategoria.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la creacion");
            alert.setHeaderText("Complete todos los campos");
            alert.showAndWait();
        }

        else if(clientes.contains(clientes1)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Login");
            alert.setHeaderText("El cliente ya existe");
            alert.showAndWait();
        }
        else{
            clientes.add(clientes1);

            Jackson.serializar(clientes,"src/main/java/com/tp/tp_final_lab3/Archives/clientes.json");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cliente creado");
            alert.setHeaderText("El cliente ha sido creado con exito");
            alert.showAndWait();
        }


    }}
    /*public void irAtras()
    {
        Jackson.serializar(observableList, pathJson);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_Venta.fxml"));
            Stage stage = (Stage) irAtrasButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}/*/

