package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class crudUsuariosController implements Initializable{

    private final String pathJson = "src/main/java/com/tp/tp_final_lab3/Archives/usuarios.json";
    private ArrayList<Usuario> usuarios;
    private ObservableList<Usuario> observableList = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJson,Usuario.class));
    @FXML
    private TableView<Usuario> tableUsuario;

    @FXML
    private TableColumn<Usuario, Integer> idColumn;

    @FXML
    private TableColumn<Usuario, String> nombreColumn;

    @FXML
    private TableColumn<Usuario, String> apellidoColumn;

    @FXML
    private TableColumn<Usuario, String> dniColumn;
    @FXML
    private TableColumn<Usuario, String> usuarioColumn;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
        usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        fechaCreacionColumn.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));

        setTableCellAlignment(idColumn);
        setTableCellAlignment(nombreColumn);
        setTableCellAlignment(apellidoColumn);
        setTableCellAlignment(dniColumn);
        setTableCellAlignment(usuarioColumn);
        setTableCellAlignment(fechaCreacionColumn);
        setTableCellAlignment(estadoColumn);

        tableUsuario.setItems(observableList);
    }
    private <S,T> void setTableCellAlignment(TableColumn<S, T> column) { //centra los datos de la tableView
        column.setCellFactory(new Callback<>() {
            @Override
            public TableCell<S, T> call(TableColumn<S, T> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            setText(item.toString());
                            setAlignment(javafx.geometry.Pos.CENTER);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }
    public void cerrarSesion()
    {
        Jackson.serializar(observableList,pathJson);//se trabaja con cache
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/ADMIN_Seleccion.fxml"));
            Stage stage = (Stage) cerrarSesionButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
