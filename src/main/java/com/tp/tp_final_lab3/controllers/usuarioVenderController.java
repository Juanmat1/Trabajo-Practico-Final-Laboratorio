package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Categorias;
import com.tp.tp_final_lab3.Models.Producto;
import com.tp.tp_final_lab3.Models.Proveedor;
import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.Services.ConsultaVenta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class usuarioVenderController implements Initializable {
    private final String pathJsonProductos = "src/main/java/com/tp/tp_final_lab3/Archives/productos.json";
    private final String pathJsonProveedores = "src/main/java/com/tp/tp_final_lab3/Archives/proveedores.json";
    private ObservableList<Producto> observableListProducto = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJsonProductos,Producto.class));
    private Producto producto = new Producto();
    @FXML
    private TableColumn<Producto, Categorias> categoriaCollum;

    @FXML
    private ComboBox<Integer> comboBoxCant;

    @FXML
    private ComboBox<String> comboBoxCategoria;

    @FXML
    private ComboBox<String> comboBoxProv;

    @FXML
    private TableColumn<Producto, String> nombreColumn;

    @FXML
    private TableColumn<Producto, String> proveedorCollum;

    @FXML
    private TableColumn<Producto, Integer> stockCollum;

    @FXML
    private TableView<Producto> tableProductos;

    @FXML
    private Button elegirButton;
    @FXML
    private Button venderButton;

    @FXML
    private Button volverButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCategorias();
        setProveedores();
    }
    public void selecCategoria(){
        ObservableList<String> productosProveedor = FXCollections.observableArrayList();
        producto.setCategoria(comboBoxCategoria.getSelectionModel().getSelectedItem());

        if(!comboBoxCategoria.getSelectionModel().isEmpty()){

            for(Producto producto : observableListProducto){
                if(comboBoxCategoria.getSelectionModel().getSelectedItem().equals(producto.getCategoria())){
                    productosProveedor.add(producto.getProveedor());
                }
            }
            comboBoxProv.setItems(productosProveedor);
        }
        ConsultaVenta.filtrarProducto(producto,tableProductos);
    }
    public void selecProveedor(){
        ObservableList<String> productosCategorias = FXCollections.observableArrayList();
        producto.setProveedor(comboBoxProv.getSelectionModel().getSelectedItem());

        if(!comboBoxProv.getSelectionModel().isEmpty()){

            for (Producto producto : observableListProducto){
                if(comboBoxProv.getSelectionModel().getSelectedItem().equals(producto.getProveedor())){
                    productosCategorias.add(producto.getCategoria());
                }
            }
            comboBoxCategoria.setItems(productosCategorias);
        }
        ConsultaVenta.filtrarProducto(producto,tableProductos);
    }
    /*public void selecCategoria(){
        ObservableList<String> productosProveedor = FXCollections.observableArrayList();
        producto.setCategoria(comboBoxCategoria.getValue());

        if(!comboBoxCategoria.getSelectionModel().isEmpty()){
            ConsultaVenta.filtrarComboBox(comboBoxProv,producto,observableListProducto);
        }
        ConsultaVenta.filtrarProducto(producto,tableProductos);
    }
    public void selecProveedor(){
        ObservableList<String> productosCategorias = FXCollections.observableArrayList();
        producto.setProveedor(comboBoxProv.getValue());

        if(!comboBoxProv.getSelectionModel().isEmpty()){
            ConsultaVenta.filtrarComboBox(comboBoxCategoria,producto,observableListProducto);
        }
        ConsultaVenta.filtrarProducto(producto,tableProductos);
    }*/
    public void buscarCantidad(){}

    public void setProveedores() {
        ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJsonProveedores, Proveedor.class));
        ObservableList<String> proveedoresString = FXCollections.observableArrayList();

        for(Proveedor proveedor : listaProveedores)
        {
            proveedoresString.add(proveedor.getNombre());
        }
        comboBoxProv.setItems(proveedoresString);

    }

    public void setCategorias() {
        ObservableList<String> categoriasString = FXCollections.observableArrayList();

        for(Categorias categoria : Categorias.values())
        {
            categoriasString.add(categoria.toString());
        }
        comboBoxCategoria.setItems(categoriasString);
    }
    @FXML
    public void Elegir(){
    }
    @FXML
    void vender() {
    }

    @FXML
    public void volver() {
        Jackson.serializar(observableListProducto,pathJsonProductos);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_Venta.fxml"));
            Stage stage = (Stage) volverButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }
}

