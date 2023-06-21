package com.tp.tp_final_lab3.controllers;
import com.tp.tp_final_lab3.Models.*;
import com.tp.tp_final_lab3.Repository.Jackson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private TextField textDomicilio;

    @FXML
    private ComboBox<CategoriaFiscal> comboBoxCategoria;

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
    private TextField textTelefono;

    @FXML
    private TextField textDNI;

    @FXML
    private TextField textCUIT;

    @FXML
    public void crearCliente() {

        clientes = Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/clientes.json", Clientes.class);
        Clientes clientes1 = new Clientes(textNombre.getText(),textApellido.getText(),textDNI.getText(),textCUIT.getText(),textDomicilio.getText(),textTelefono.getText(),comboBoxCategoria.getSelectionModel().getSelectedItem(), EstadosPersona.Activo);

        if(textCUIT.getText().isEmpty() || textDomicilio.getText().isEmpty() || textTelefono.getText().isEmpty()
                || textNombre.getText().isEmpty() || textApellido.getText().isEmpty() || textDNI.getText().isEmpty() || comboBoxCategoria.getSelectionModel().isEmpty())
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

    }
    @FXML
    public void setCategorias() {
        ObservableList<String> categoriasString = FXCollections.observableArrayList();

        for(CategoriaFiscal categoria : Categorias.values())
        {
            categoriasString.add(categoria.toString());
        }
        comboBoxCategoria.setItems(categoriasString);
    }
    public void irAtras()
    {
        //Jackson.serializar(observableList, pathJson);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_Venta.fxml"));
            Stage stage = (Stage) irAtrasButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

}
