package com.tp.tp_final_lab3.controllers;
import com.tp.tp_final_lab3.Models.*;
import com.tp.tp_final_lab3.Repository.Jackson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class createClientController implements Initializable {

    ArrayList<Clientes> clientes;
@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    setCategorias();

    }

    private String opcionElegida;

    private ChangeListener<String> opcionSeleccionadaListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue != null) {
                opcionElegida = newValue;
            }
        }
    };



    @FXML
    private TextField textDomicilio;

    @FXML
    private ComboBox<String> comboBoxCategoria;


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
        Clientes clientes1 = new Clientes(textNombre.getText(),textApellido.getText(),textDNI.getText(),textCUIT.getText(),textDomicilio.getText(),textTelefono.getText(), Clientes.Estado.Activo,comboBoxCategoria.getSelectionModel().getSelectedItem());

        if(textCUIT.getText().isEmpty() || textDomicilio.getText().isEmpty() || textTelefono.getText().isEmpty()
                || textNombre.getText().isEmpty() || textApellido.getText().isEmpty() || textDNI.getText().isEmpty() || comboBoxCategoria.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la creacion");
            alert.setHeaderText("Complete todos los campos");
            alert.showAndWait();
        }

        else if (clientes.contains(clientes1)){
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

        for(CategoriaFiscal categoria : CategoriaFiscal.values())
        {
            categoriasString.add(categoria.getDescripcion());
        }
        comboBoxCategoria.setItems(categoriasString);
    }
    @FXML
    private void mostrarOpciones(ActionEvent event) {

        comboBoxCategoria.show();
    }

    @FXML
    public void cargarCategorias() {

        setCategorias();

        comboBoxCategoria.getSelectionModel().selectedItemProperty().addListener(opcionSeleccionadaListener);
    }

    public void irAtras()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_Venta.fxml"));
            Stage stage = (Stage) irAtrasButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }


}
