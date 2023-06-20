package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Categorias;
import com.tp.tp_final_lab3.Models.Producto;
import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.Services.ControllersMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminProductosCrudController implements Initializable {

    private final String pathJson = "src/main/java/com/tp/tp_final_lab3/Archives/productos.json";
    private final ObservableList<Producto> observableList = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJson, Producto.class));

    //region FXML
    @FXML
    private Button limpiarButton;
    @FXML
    private Button actualizarButton;
    @FXML
    private Button agregarButton;
    @FXML
    private Button borrarButton;

    @FXML
    private Button volverButton;

    @FXML
    private ComboBox<String> proveedorComboBox;
    @FXML
    private ComboBox<String> categoriaComboBox;
    @FXML
    private CheckBox estadoCheckBox;
    @FXML
    private TableColumn<Producto, String> categoriaCollum;
    @FXML
    private TableColumn<Producto,Integer > estadoColumn;
    @FXML
    private TableColumn<Producto,String > nombreColumn;
    @FXML
    private TableColumn<Producto,Integer> idColumn;
    @FXML
    private TableColumn<Producto,Integer> stockCollum;
    @FXML
    private TableColumn<Producto,String> proveedorCollum;
    @FXML
    private TableView<Producto> tableProveedores;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField stockTextArea;

    //endregion
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        setCategorias();
        setProveedores();

        categoriaCollum.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        stockCollum.setCellValueFactory(new PropertyValueFactory<>("stock"));
        proveedorCollum.setCellValueFactory(new PropertyValueFactory<>("proveedor"));

        ControllersMethods.alinearTabla(categoriaCollum);
        ControllersMethods.alinearTabla(nombreColumn);
        ControllersMethods.alinearTabla(idColumn);
        ControllersMethods.alinearTabla(estadoColumn);
        ControllersMethods.alinearTabla(stockCollum);
        ControllersMethods.alinearTabla(proveedorCollum);

        tableProveedores.setItems(observableList);

    }

    //region Metodos auxiliares
    public Producto.Estado obtenerEstado(){
        if(estadoCheckBox.isSelected()){
            return Producto.Estado.Activo;
        }else{
            return Producto.Estado.Inactivo;
        }
    }

    public boolean obtenerBooleanEstado(Producto producto){
        return producto.getEstado().equals(Producto.Estado.Activo);
    }

    private boolean checkCampos()
    {
        boolean status = false;

        if(nombreTextField.getText().isEmpty() || categoriaComboBox.getSelectionModel().isEmpty()
                || proveedorComboBox.getSelectionModel().isEmpty()||stockTextArea.getText().isEmpty() )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en los campos,reviselos");
            alert.setContentText("Algunos de los campos esta vacio");
            alert.showAndWait();
        }
        else{

            status= true;
        }
        return status;
    }

    public void setProveedores()
    {


        ObservableList<String> listaProveedor = FXCollections.observableArrayList();

        for(Producto producto : observableList)
        {
            listaProveedor.add(producto.getProveedor());
        }
        proveedorComboBox.setItems(listaProveedor);

    }

    public void setCategorias()
    {
        ObservableList<String> listaCategoria = FXCollections.observableArrayList();

        for(Categorias categoria : Categorias.values())
        {
            listaCategoria.add(categoria.toString());
        }
        categoriaComboBox.setItems(listaCategoria);
    }



    //endregion
    public void agregar()
    {
        if (checkCampos()) {
            try {
                Producto producto = new Producto(nombreTextField.getText(),
                        categoriaComboBox.getSelectionModel().getSelectedItem(),
                        proveedorComboBox.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(stockTextArea.getText()),obtenerEstado());
                if (observableList.contains(producto)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("No se puede agregar un usuario ya existente");
                    alert.showAndWait();
                }else {
                    observableList.add(producto);
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
        limpiar();

    }

    public void borrar()
    {
        observableList.remove(tableProveedores.getSelectionModel().getSelectedItem());
    }

    public void limpiar()
    {
        nombreTextField.clear();
        proveedorComboBox.getSelectionModel().clearSelection();
        categoriaComboBox.getSelectionModel().clearSelection();
        stockTextArea.clear();
    }

    public void actualizar()
    {
        Producto producto = tableProveedores.getSelectionModel().getSelectedItem();
        nombreTextField.setText(producto.getNombre());
        estadoCheckBox.setSelected(obtenerBooleanEstado(producto));
        stockTextArea.setText(Integer.toString(producto.getStock()));
        actualizarButton.setText("Guardar");
        actualizarButton.setOnAction(event->{
            modficarDatos(producto);
        });
    }

    public void modficarDatos(Producto producto)
    {
        if(checkCampos())
        {
            producto.setNombre(nombreTextField.getText());
            producto.setCategoria(categoriaComboBox.getSelectionModel().getSelectedItem());
            producto.setProveedor(proveedorComboBox.getSelectionModel().getSelectedItem());
            producto.setEstado(obtenerEstado());
            producto.setStock(Integer.parseInt(stockTextArea.getText()));
            observableList.set(observableList.indexOf(producto),producto);
            limpiar();
            actualizarButton.setText("Actualizar");
            actualizarButton.setOnAction(event ->{
                actualizar();
            });
        }
    }

    public void salir()

    {
        Jackson.serializar(observableList,pathJson);//cache

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
