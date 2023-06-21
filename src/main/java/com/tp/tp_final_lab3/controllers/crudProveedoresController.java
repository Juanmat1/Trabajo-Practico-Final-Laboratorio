package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Categorias;
import com.tp.tp_final_lab3.Models.Proveedor;
import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.Services.ControllersMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class crudProveedoresController implements Initializable,ICrud{

    private final String pathJson = "src/main/java/com/tp/tp_final_lab3/Archives/proveedores.json";
    private ObservableList<Proveedor> observableList = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJson,Proveedor.class));
    @FXML
    private Button actualizarButton;

    @FXML
    private Button agregarButton;

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField razonSocialTextField;
    @FXML
    private TextField cuitTextField;

    @FXML
    private Button borrarButton;

    @FXML
    private TableColumn<Proveedor, String> cuitColumn;

    @FXML
    private CheckBox estadoCheckBox;

    @FXML
    private TableColumn<Proveedor, Proveedor.Estado> estadoColumn;

    @FXML
    private TableColumn<Proveedor, Integer> idColumn;

    @FXML
    private Button limpiarButton;

    @FXML
    private TableColumn<Proveedor, String> nombreColumn;

    @FXML
    private TableColumn<Proveedor, String> razonSocialColumn;

    @FXML
    private TableView<Proveedor> tableProveedor;

    @FXML
    private Button volverButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        razonSocialColumn.setCellValueFactory(new PropertyValueFactory<>("razonSocial"));
        cuitColumn.setCellValueFactory(new PropertyValueFactory<>("cuit"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));

        ControllersMethods.alinearTabla(idColumn);
        ControllersMethods.alinearTabla(nombreColumn);
        ControllersMethods.alinearTabla(nombreColumn);
        ControllersMethods.alinearTabla(cuitColumn);
        ControllersMethods.alinearTabla(estadoColumn);

        tableProveedor.setItems(observableList);
    }
    @Override
    public void agregar() {
        if(checkCampos()){
            try {
                ArrayList<Proveedor>provs = Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/proveedores.json", Proveedor.class);
                int lastId = createProvController.obtenerIdMasGrande(provs);
                Proveedor proveedor = new Proveedor(lastId+1,nombreTextField.getText(),razonSocialTextField.getText(),cuitTextField.getText(),obtenerEstado());
                if (observableList.contains(proveedor)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("No se puede agregar un usuario ya existente");
                    alert.showAndWait();
                }else {
                    observableList.add(proveedor);
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en los campos,reviselos");
                alert.setContentText("Algunos de los campos es " +
                        "incorrecto revise de no poner letras en los campos con numeros");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
        actualizarButton.setText("Actualizar");
        actualizarButton.setOnAction(event -> actualizar());
        limpiar();
    }
    public Proveedor.Estado obtenerEstado(){
        if(estadoCheckBox.isSelected()){
            return Proveedor.Estado.Activo;
        }else{
            return Proveedor.Estado.Inactivo;
        }
    }
    @Override
    public boolean checkCampos(){
        if (ControllersMethods.checkTxtField(nombreTextField,razonSocialTextField,cuitTextField)){
            ControllersMethods.alertaCampos();
            return false;
        }else{
            return true;
        }
    }
    @Override
    public void actualizar() {
        Proveedor proveedor = tableProveedor.getSelectionModel().getSelectedItem();
        if(proveedor != null) {
            nombreTextField.setText(proveedor.getNombre());
            razonSocialTextField.setText(proveedor.getRazonSocial());
            cuitTextField.setText(proveedor.getCuit());
            estadoCheckBox.setSelected(obtenerBooleanEstado(proveedor));

            actualizarButton.setText("Guardar");

            actualizarButton.setOnAction(event -> {
                modificar(proveedor);
            });
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error para actualizar");
            alert.setContentText("Ningun usuario seleccionado");
            alert.showAndWait();
        }
    }
    public boolean obtenerBooleanEstado(Proveedor proveedor){
        if(proveedor.getEstado().equals(Proveedor.Estado.Activo)){
            return true;
        }else{
            return false;
        }
    }
    public void modificar(Proveedor proveedor){

        if(ControllersMethods.checkTxtField(nombreTextField,razonSocialTextField,cuitTextField)) {
            proveedor.setNombre(nombreTextField.getText());
            proveedor.setRazonSocial(razonSocialTextField.getText());
            proveedor.setCuit(cuitTextField.getText());
            proveedor.setEstado(obtenerEstado());
            observableList.set(observableList.indexOf(proveedor),proveedor);
        }
        limpiar();
        actualizarButton.setText("Actualizar");
        actualizarButton.setOnAction(event -> actualizar());
    }

    @Override
    public void borrar() {
        observableList.remove(tableProveedor.getSelectionModel().getSelectedItem());
    }

    @Override
    public void limpiar() {
        ControllersMethods.limpiarTxtField(nombreTextField,razonSocialTextField,cuitTextField);
        estadoCheckBox.setSelected(false);
    }

    public void volver() {
        Jackson.serializar(observableList,pathJson);//se trabaja con cache
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/ADMIN_Seleccion.fxml"));
            Stage stage = (Stage) volverButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
