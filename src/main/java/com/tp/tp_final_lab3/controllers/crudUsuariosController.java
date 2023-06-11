package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class crudUsuariosController {
    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<Usuario, ?> idColumn;

    @FXML
    private TableColumn<Usuario, ?> nombreColumn;

    @FXML
    private TableColumn<Usuario, ?> apellidoColumn;

    @FXML
    private TableColumn<Usuario, ?> contraseniaColumn;

    @FXML
    private TableColumn<Usuario, ?> fechaCreacionColumn;

    @FXML
    private TableColumn<Usuario, ?> estadoColumn;

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
