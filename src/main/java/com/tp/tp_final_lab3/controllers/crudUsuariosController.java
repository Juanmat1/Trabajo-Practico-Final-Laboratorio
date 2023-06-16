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
    private TextField dniTextField;

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
    public boolean checkCampos()
    {
        boolean status = false;

        if(usuarioTextField.getText().isEmpty() || nombreTextField.getText().isEmpty() ||
                apellidoColumn.getText().isEmpty() || contraseniaTextField.getText().isEmpty() ||
                dniTextField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en los campos,reviselos");
            alert.setContentText("Algunos de los campos esta vacio");
            alert.showAndWait();
        }
        else {
            status = true;
        }
        return status;
    }
    //String nombre, String apellido, String dni, String usuario, String contrasenia,Estado estado
    public Usuario.Estado obtenerEstado(){
        if(estadoCheckBox.isSelected()){
            return Usuario.Estado.Activo;
        }else{
            return Usuario.Estado.Inactivo;
        }
    }
    public boolean obtenerBooleanEstado(Usuario usuario){
        if(usuario.getEstado().equals(Usuario.Estado.Activo)){
            return true;
        }else{
            return false;
        }
    }
    public void agregar() {

        if (checkCampos()) {
            try {
                Usuario.getUltimoUsersID();
                Usuario usuario = new Usuario(nombreTextField.getText(),apellidoTextField.getText(),dniTextField.getText(),
                        usuarioTextField.getText(),contraseniaTextField.getText(),obtenerEstado());
                observableList.add(usuario);

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en los campos,reviselos");
                alert.setContentText("Algunos de los campos es " +
                        "incorrecto revise de no poner letras en los campos con numeros");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
        limpiar();
    }
    public void actualizar(){
        Usuario usuario = tableUsuario.getSelectionModel().getSelectedItem();
        nombreTextField.setText(usuario.getNombre());
        apellidoTextField.setText(usuario.getApellido());
        dniTextField.setText(usuario.getDni());
        usuarioTextField.setText(usuario.getUsuario());
        contraseniaTextField.setText(usuario.getContrasenia());
        estadoCheckBox.setSelected(obtenerBooleanEstado(usuario));

        actualizarButton.setText("Guardar");

        actualizarButton.setOnAction(event -> {
            modificarDatos(usuario);
        });
    }
    public void modificarDatos(Usuario usuario){

        if(checkCampos()) {
            usuario.setNombre(nombreTextField.getText());
            usuario.setApellido(apellidoTextField.getText());
            usuario.setDni(apellidoTextField.getText());
            usuario.setUsuario(usuarioTextField.getText());
            usuario.setContrasenia(usuarioTextField.getText());
            usuario.setEstado(obtenerEstado());
            observableList.set(observableList.indexOf(usuario),usuario);

            limpiar();
            actualizarButton.setText("Actualizar");
            actualizarButton.setOnAction(event -> actualizar());
        }
    }
    public void borrar(){

        observableList.remove(tableUsuario.getSelectionModel().getSelectedItem());
    }
    public void limpiar(){
        usuarioTextField.clear();
        nombreTextField.clear();
        apellidoTextField.clear();
        contraseniaTextField.clear();
        dniTextField.clear();
        estadoCheckBox.setSelected(false);
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
