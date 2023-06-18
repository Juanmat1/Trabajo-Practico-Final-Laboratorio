package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Categorias;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.Arrays;

public class createProvController {

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textApellido;

    @FXML
    private TextField textDNI;

    @FXML
    private Button createButton;

    @FXML
    private TextField textUser;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private Button loginButton;

    @FXML
    private void mostrarOpciones(ActionEvent event) {
        initialize();
        categoryChoiceBox.show();
    }

    @FXML
    public void initialize() {

        ArrayList<Categorias> opciones = new ArrayList<>(Arrays.asList(Categorias.values()));

        for (Categorias categoria : opciones) {
            categoryChoiceBox.getItems().add(categoria.toString());
        }
    }

    @FXML
    private void crearCuenta() {
        // Lógica para crear la cuenta de proveedor
    }

    @FXML
    private void irLogin() {
        // Lógica para ir a la vista de login
    }

    // ... otros métodos y eventos ...
}
