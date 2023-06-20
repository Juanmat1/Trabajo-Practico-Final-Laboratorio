package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Categorias;
import com.tp.tp_final_lab3.Models.Proveedor;
import com.tp.tp_final_lab3.Repository.Jackson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class createProvController {

    ArrayList<Proveedor> provs;
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
    private TextField textNombre;

    @FXML
    private TextField textRazonSocial;

    @FXML
    private TextField textCUIT;

    @FXML
    private Button createButton;

    @FXML
    private ComboBox<String> categoryChoice;

    @FXML
    private Button loginButton;

    @FXML
    private void mostrarOpciones(ActionEvent event) {

        categoryChoice.show();
    }

    @FXML
    public void cargarCategorias() {

        setCategorias();

        categoryChoice.getSelectionModel().selectedItemProperty().addListener(opcionSeleccionadaListener);
    }

    @FXML
    public String obtenerOpcionSeleccionada() {
        return categoryChoice.getValue();
    }

    @FXML
    public void crearCuenta() throws ClassNotFoundException {

        provs = Jackson.deserializarArrayList("C:\\Users\\Avalith\\Desktop\\tp lab III\\TP_FINAL_LAB3\\src\\main\\java\\com\\tp\\tp_final_lab3\\Archives\\proveedores.json", Proveedor.class);
        int lastId = obtenerIdMasGrande(provs);


        Proveedor prov = new Proveedor(lastId + 1, textNombre.getText(), textRazonSocial.getText(), textCUIT.getText(), Categorias.valueOf(opcionElegida));

        if (textNombre.getText().isEmpty() || textRazonSocial.getText().isEmpty() || textCUIT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Nombre");
            alert.setHeaderText("Complete todos los campos");
            alert.showAndWait();
        } else if (provs.contains(prov)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("El Proveedor ya existe");
            alert.showAndWait();
        } else {
            provs.add(prov);

            Jackson.serializar(provs, "C:\\Users\\Avalith\\Desktop\\tp lab III\\TP_FINAL_LAB3\\src\\main\\java\\com\\tp\\tp_final_lab3\\Archives\\proveedores.json");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cuenta Creada");
            alert.setHeaderText("Proveedor creado con éxito");
            alert.showAndWait();
        }
    }


    @FXML
    public void irLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/LOGIN_importadora.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    public void setCategorias() {
        ObservableList<String> listaCategoria = FXCollections.observableArrayList();
        categoryChoice.setPromptText("Categorías");
        for (Categorias categoria : Categorias.values()) {
            listaCategoria.add(categoria.toString());
        }
        categoryChoice.setItems(listaCategoria);
    }

    public static int obtenerIdMasGrande(ArrayList<Proveedor> proveedores) {
        int maxId = 0;
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getId() > maxId) {
                maxId = proveedor.getId();
            }
        }
        return maxId;
    }


 /*   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }*/

    // ... otros métodos y eventos ...
}
