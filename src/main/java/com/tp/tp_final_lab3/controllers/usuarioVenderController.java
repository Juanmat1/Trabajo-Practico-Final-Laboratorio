package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Categorias;
import com.tp.tp_final_lab3.Models.Producto;
import com.tp.tp_final_lab3.Models.Proveedor;
import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.Services.ConsultaVenta;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class usuarioVenderController implements Initializable {
    private final String pathJsonProductos = "src/main/java/com/tp/tp_final_lab3/Archives/productos.json";
    private final String pathJsonProveedores = "src/main/java/com/tp/tp_final_lab3/Archives/proveedores.json";
    private ObservableList<Producto> observableListProducto = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJsonProductos,Producto.class));
    private Producto producto = new Producto();

    @FXML
    private ComboBox<Integer> comboBoxCant;

    @FXML
    private ComboBox<String> comboBoxCategoria;

    @FXML
    private ComboBox<String> comboBoxProv;
    @FXML
    private TableColumn<Producto, Categorias> categoriaCollum;

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

        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        categoriaCollum.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        proveedorCollum.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        stockCollum.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableProductos.setItems(observableListProducto);

        ControllersMethods.alinearTabla(nombreColumn);
        ControllersMethods.alinearTabla(categoriaCollum);
        ControllersMethods.alinearTabla(proveedorCollum);
        ControllersMethods.alinearTabla(stockCollum);
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
        ConsultaVenta.filtrarProducto(producto,tableProductos,observableListProducto);
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
        ConsultaVenta.filtrarProducto(producto,tableProductos,observableListProducto);
    }

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
        Producto producto = tableProductos.getSelectionModel().getSelectedItem();
        if(producto != null){
            comboBoxCategoria.setValue(producto.getCategoria().toString());
            comboBoxProv.setValue(producto.getProveedor());

            ObservableList<Integer> cantidad = FXCollections.observableArrayList();
            for(int i = 1; i<=producto.getStock() ; i++){
                cantidad.add(i);
            }
            comboBoxCant.setItems(cantidad);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error para elegir");
            alert.setContentText("Ningun producto seleccionado");
            alert.showAndWait();
        }
    }
    public void selecCantidad(){
    }
    @FXML
    void vender() {
        Producto producto = tableProductos.getSelectionModel().getSelectedItem();
        if(producto != null && !comboBoxCant.getSelectionModel().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("Seguro que quieres vender?");
            alert.setContentText("Esta acción no se puede deshacer.");

            ButtonType buttonTypeSi = new ButtonType("Sí");
            ButtonType buttonTypeNo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeSi) {

                producto.setStock(producto.getStock()-comboBoxCant.getValue());
                int index = observableListProducto.indexOf(producto);
                observableListProducto.set(index,producto);

                alert.close();
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Venta");
                alert2.setContentText("Venta realizada con exito");
                alert2.showAndWait();

                tableProductos.setItems(observableListProducto);
                comboBoxCategoria.setValue(null);
                comboBoxProv.setValue(null);
                comboBoxCant.setValue(null);

            } else {
                alert.close();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error para vender");
            alert.setContentText("Ningun producto o cantidad seleccionada");
            alert.showAndWait();
        }
    }

    @FXML
    public void volver() {
        Jackson.serializar(observableListProducto,pathJsonProductos);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_VerificarCliente.fxml"));
            Stage stage = (Stage) volverButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }
}

