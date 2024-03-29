package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.CategoriaFiscal;
import com.tp.tp_final_lab3.Models.Clientes;
import com.tp.tp_final_lab3.Models.Delta;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.Services.ControllersMethods;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class crudClientesController implements Initializable {

    private final String pathJson = "src/main/java/com/tp/tp_final_lab3/Archives/clientes.json";
    private  final ObservableList<Clientes> observableList = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJson, Clientes.class));
    @FXML
    private TableView<Clientes> tableCliente;
    @FXML
    private TableColumn<Clientes, Integer> idColumn;
    @FXML
    private TableColumn<Clientes, String> nombreColumn;
    @FXML
    private TableColumn<Clientes, String> apellidoColumn;
    @FXML
    private TableColumn<Clientes, String> dniColumn;
    @FXML
    private TableColumn<Clientes, String> cuitColumn;
    @FXML
    private TableColumn<Clientes, String> telefonoColumn;
    @FXML
    private TableColumn<Clientes, String> domicilioColumn;
    @FXML
    private TableColumn<Clientes, LocalDate> fechaCreacionColumn;
    @FXML
    private TableColumn<Clientes, Clientes.Estado> estadoColumn;
    @FXML
    private TableColumn<Clientes, CategoriaFiscal> categoriaColumn;
    @FXML
    private Button agregarButton;
    @FXML
    private Button actualizarButton;
    @FXML
    private Button limpiarButton;
    @FXML
    private Button volverButton;
    @FXML
    private Button borrarButton;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField cuitTextField;
    @FXML
    private TextField telefonoTextField;
    @FXML
    private TextField dniTextField;
    @FXML
    private TextField apellidoTextField;
    @FXML
    private TextField domicilioTextField;

    @FXML
    private ComboBox<String> comboBoxCategoria;
    @FXML
    private CheckBox estadoCheckBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCategorias();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
        cuitColumn.setCellValueFactory(new PropertyValueFactory<>("cuit"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        domicilioColumn.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        fechaCreacionColumn.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));

        ControllersMethods.alinearTabla(idColumn);
        ControllersMethods.alinearTabla(nombreColumn);
        ControllersMethods.alinearTabla(apellidoColumn);
        ControllersMethods.alinearTabla(dniColumn);
        ControllersMethods.alinearTabla(cuitColumn);
        ControllersMethods.alinearTabla(telefonoColumn);
        ControllersMethods.alinearTabla(domicilioColumn);
        ControllersMethods.alinearTabla(categoriaColumn);
        ControllersMethods.alinearTabla(fechaCreacionColumn);
        ControllersMethods.alinearTabla(estadoColumn);

        tableCliente.setItems(observableList);
    }

    public void agregar() {
        if (checkCampos()) {
            if (ControllersMethods.contieneNumeros(nombreTextField.getText(), apellidoTextField.getText()) || ControllersMethods.contieneLetras(dniTextField.getText(), cuitTextField.getText(),telefonoTextField.getText())) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error para agregar");
                alert.setContentText("Verifique de poner caracteres validos");
                alert.showAndWait();
            } else {
                try {

                    Clientes clientes = new Clientes(nombreTextField.getText(), apellidoTextField.getText(),
                            dniTextField.getText(), cuitTextField.getText(), domicilioTextField.getText(), telefonoTextField.getText(),
                            Clientes.Estado.Activo, comboBoxCategoria.getSelectionModel().getSelectedItem());

                    if (observableList.contains(clientes)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("No se puede agregar un cliente ya existente");
                        alert.showAndWait();
                        actualizarButton.setText("Actualizar");
                        actualizarButton.setOnAction(event -> actualizar());
                    } else {
                        observableList.add(clientes);
                        System.out.println("entro");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        limpiar();


    }

    private String opcionElegida;

    private final ChangeListener<String> opcionSeleccionadaListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue != null) {
                opcionElegida = newValue;
            }
        }
    };

    public void actualizar() {
        Clientes clientes = tableCliente.getSelectionModel().getSelectedItem();
        if (clientes != null) {
            nombreTextField.setText(clientes.getNombre());
            apellidoTextField.setText(clientes.getApellido());
            dniTextField.setText(clientes.getDni());
            cuitTextField.setText(clientes.getCuit());
            domicilioTextField.setText(clientes.getDomicilio());
            telefonoTextField.setText(clientes.getTelefono());
            estadoCheckBox.setSelected(obtenerBooleanEstado(clientes));

            actualizarButton.setText("Guardar");

            actualizarButton.setOnAction(event -> {
                modificarDatos(clientes);
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error para actualizar");
            alert.setContentText("Ningun cliente seleccionado");
            alert.showAndWait();
        }
    }

    public boolean checkCampos() {
        if (ControllersMethods.checkTxtField(nombreTextField, apellidoTextField, dniTextField, cuitTextField, telefonoTextField, domicilioTextField)
                || comboBoxCategoria.getSelectionModel().isEmpty()) {

            ControllersMethods.alertaCampos();
            return false;
        } else {
            return true;
        }
    }

    public Clientes.Estado obtenerEstado() {
        if (estadoCheckBox.isSelected()) {
            return Clientes.Estado.Activo;
        } else {
            return Clientes.Estado.Inactivo;
        }
    }

    public boolean obtenerBooleanEstado(Clientes clientes) {
        if (clientes.getEstado().equals(Clientes.Estado.Activo)) {
            return true;
        } else {
            return false;
        }
    }

    public void modificarDatos(Clientes clientes) {

        if (checkCampos()) {
            if (ControllersMethods.contieneNumeros(nombreTextField.getText(), apellidoTextField.getText()) || ControllersMethods.contieneLetras(dniTextField.getText(), cuitTextField.getText(),telefonoTextField.getText())) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error para agregar");
                alert.setContentText("Verifique de no poner caracteres invalidos");
                alert.showAndWait();
            }else {

                clientes.setNombre(nombreTextField.getText());
                clientes.setApellido(apellidoTextField.getText());
                clientes.setDni(dniTextField.getText());
                clientes.setCuit(cuitTextField.getText());
                clientes.setDomicilio(domicilioTextField.getText());
                clientes.setTelefono(telefonoTextField.getText());
                clientes.setEstado(obtenerEstado());
                clientes.setCategoria(comboBoxCategoria.getSelectionModel().getSelectedItem());
                observableList.set(observableList.indexOf(clientes), clientes);
            }
        }
        limpiar();
        actualizarButton.setText("Actualizar");
        actualizarButton.setOnAction(event -> actualizar());
    }
    
    public void borrar(){
        Clientes clientes= tableCliente.getSelectionModel().getSelectedItem();
        clientes.setEstado(Clientes.Estado.Inactivo);
        observableList.set(observableList.indexOf(clientes),clientes);
    }

    public void limpiar() {
        ControllersMethods.limpiarTxtField(nombreTextField, apellidoTextField, dniTextField, cuitTextField, telefonoTextField, domicilioTextField);
        estadoCheckBox.setSelected(false);
        comboBoxCategoria.getSelectionModel().clearSelection();
    }

    public void volver() {
        Jackson.serializar(observableList, pathJson);//se trabaja con cache
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/ADMIN_Seleccion.fxml"));
            Stage stage = (Stage) volverButton.getScene().getWindow();

            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @FXML
    public void setCategorias() {
        ObservableList<String> categoriasString = FXCollections.observableArrayList();

        for (CategoriaFiscal categoria : CategoriaFiscal.values()) {
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


}


