package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.*;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class adminProductosCrudController implements Initializable {

    //region LISTAS
    private final String pathJsonProv = "src/main/java/com/tp/tp_final_lab3/Archives/proveedores.json";
    private final String pathJsonProduc = "src/main/java/com/tp/tp_final_lab3/Archives/productos.json";
    private final ObservableList<Proveedor> observableListProv = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJsonProv, Proveedor.class));
    private final ObservableList<Producto> observableListProd = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJsonProduc, Producto.class));
    //endregion

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
    private TableView<Producto> tableProductos;
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
        setTabla();
        alinearTabla();

        estadoCheckBox.setSelected(true);
        tableProductos.setItems(observableListProd);

    }

    //region METODOS PRINCIPALES
    public void agregar()
    {
        if (checkCampos()) {
            try {
                Producto producto = new Producto(nombreTextField.getText(),
                        categoriaComboBox.getSelectionModel().getSelectedItem(),
                        proveedorComboBox.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(stockTextArea.getText()),obtenerEstado());
                if (busquedaProducto(producto.getNombre(),producto.getCategoria()))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("No se puede agregar un producto ya existente");
                    alert.showAndWait();
                }else {
                    observableListProd.add(producto);
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
        observableListProd.remove(tableProductos.getSelectionModel().getSelectedItem());
    }

    public void limpiar()
    {
        nombreTextField.clear();
        proveedorComboBox.getSelectionModel().clearSelection();
        categoriaComboBox.getSelectionModel().clearSelection();
        stockTextArea.clear();
        estadoCheckBox.setSelected(true);
    }

    public void actualizar()
    {
        Producto producto = tableProductos.getSelectionModel().getSelectedItem();
        nombreTextField.setText(producto.getNombre());
        estadoCheckBox.setSelected(obtenerBooleanEstado(producto));
        stockTextArea.setText(Integer.toString(producto.getStock()));
        proveedorComboBox.getSelectionModel().select(obtenerIndexProveedor(producto.getProveedor()));
        categoriaComboBox.getSelectionModel().select(obtenerIndexCategoria(producto.getCategoria()));

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

            observableListProd.set(observableListProd.indexOf(producto),producto);
            limpiar();
            actualizarButton.setText("Actualizar");
            actualizarButton.setOnAction(event ->{
                actualizar();
            });
        }
    }

    public void salir()
    {
        Jackson.serializar(observableListProd,pathJsonProduc);//cache

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

//endregion


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

        for(Proveedor proveedor : observableListProv )
        {
            listaProveedor.add(proveedor.getRazonSocial());
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

    public void setTabla()
    {
        categoriaCollum.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        stockCollum.setCellValueFactory(new PropertyValueFactory<>("stock"));
        proveedorCollum.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
    }

    public void alinearTabla()
    {
        ControllersMethods.alinearTabla(categoriaCollum);
        ControllersMethods.alinearTabla(nombreColumn);
        ControllersMethods.alinearTabla(idColumn);
        ControllersMethods.alinearTabla(estadoColumn);
        ControllersMethods.alinearTabla(stockCollum);
        ControllersMethods.alinearTabla(proveedorCollum);
    }

    public boolean busquedaProducto(String nombre, String categoria) {
        boolean flag = false;
        nombre = nombre.toLowerCase().replaceAll("\\s","");
        categoria = categoria.toLowerCase().replaceAll("\\s","");

        for(Producto producto : observableListProd)
        {
            if(producto.getCategoria().toLowerCase().replaceAll("\\s","").equals(categoria)
                    && producto.getNombre().toLowerCase().replaceAll("\\s","").equals(nombre))
            {
                flag = true;
                break;
            }
        }

        return  flag;
    }

    public int obtenerIndexCategoria(String categoria)
    {
        int index = 0;

        for(String string : categoriaComboBox.getItems())
        {

            if(string.equals(categoria))
            {
                break;
            }
            index++;
        }
        return  index;
    }

    public int obtenerIndexProveedor(String proveedor)
    {
        int index = 0;

        for(String string : proveedorComboBox.getItems())
        {
            if(string.equals(proveedor))
            {
                break;
            }
            index++;
        }

        return index;
    }




    //endregion



}
