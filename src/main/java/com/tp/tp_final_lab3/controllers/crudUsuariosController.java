package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class crudUsuariosController {
    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<Usuario, Integer> idColumn;

    @FXML
    private TableColumn<Usuario, String> nombreColumn;

    @FXML
    private TableColumn<Usuario, String> apellidoColumn;

    @FXML
    private TableColumn<Usuario, String> contraseniaColumn;

    @FXML
    private TableColumn<Usuario, LocalDate> fechaCreacionColumn;

    @FXML
    private TableColumn<Usuario, Usuario.Estado> estadoColumn;

    @FXML
    private Button agregarButton;

    @FXML
    private Button actualizarButton;

    @FXML
    private Button limpiarButton;

    @FXML
    private Button borrarButton;

    @FXML
    private TextField usuarioTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField contraseniaTextField;

    @FXML
    private TextField origenTextField;

    @FXML
    private CheckBox estadoCheckBox;

    @FXML
    private TextField apellidoTextField;

    @FXML
    private Button cerrarSesionButton;
}
