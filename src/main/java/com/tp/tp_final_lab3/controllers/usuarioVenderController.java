package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.*;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.Services.ConsultaVenta;
import com.tp.tp_final_lab3.Services.ControllersMethods;
import com.tp.tp_final_lab3.Services.Popup;
import com.tp.tp_final_lab3.SingletonClasses.SIngletonTicketClass;
import com.tp.tp_final_lab3.SingletonClasses.SingletonClienteClass;
import com.tp.tp_final_lab3.SingletonClasses.SingletonUsuarioClass;
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
import java.time.LocalDate;
import java.util.*;

public class usuarioVenderController implements Initializable {
    private final String pathJsonProductos = "src/main/java/com/tp/tp_final_lab3/Archives/productos.json";
    private final String pathJsonProveedores = "src/main/java/com/tp/tp_final_lab3/Archives/proveedores.json";
    private final ObservableList<Producto> observableListProducto = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJsonProductos, Producto.class));

    private final ObservableList<Ticket> observableTicket =
            FXCollections.observableArrayList(Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/tickets.json", Ticket.class));
    private final Producto producto = new Producto();
    @FXML
    private ComboBox<Integer> comboBoxCant;
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
    private TableColumn<Producto,Double> precioColumn;

    @FXML
    private TableView<Producto> tableProductos;

    @FXML
    private Button elegirButton;
    @FXML
    private Button venderButton;

    @FXML
    private Button volverButton;

    @FXML
    private TextField textPrecio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProveedores();

        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        categoriaCollum.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        proveedorCollum.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        stockCollum.setCellValueFactory(new PropertyValueFactory<>("stock"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tableProductos.setItems(observableListProducto);

        ControllersMethods.alinearTabla(nombreColumn);
        ControllersMethods.alinearTabla(categoriaCollum);
        ControllersMethods.alinearTabla(proveedorCollum);
        ControllersMethods.alinearTabla(stockCollum);
        ControllersMethods.alinearTabla(precioColumn);
    }

    @FXML
    public void selecProveedor() {
        ObservableList<String> productosCategorias = FXCollections.observableArrayList();
        producto.setProveedor(comboBoxProv.getSelectionModel().getSelectedItem());

        if (!comboBoxProv.getSelectionModel().isEmpty()) {

            for (Producto producto : observableListProducto) {
                if (comboBoxProv.getSelectionModel().getSelectedItem().equals(producto.getProveedor())) {
                    productosCategorias.add(producto.getCategoria());
                }
            }
        }
        ConsultaVenta.filtrarProducto(producto, tableProductos, observableListProducto);
    }

    @FXML
    public void setProveedores() {
        //ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJsonProveedores, Proveedor.class));
        ObservableList<Producto> listaProveedores = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJsonProductos, Producto.class));
        ObservableList<String> proveedoresString = FXCollections.observableArrayList();

        for (Producto proveedor : listaProveedores) {
            proveedoresString.add(proveedor.getProveedor());
        }
        comboBoxProv.setItems(proveedoresString);

    }

    @FXML
    public void Elegir() {
        Producto producto = tableProductos.getSelectionModel().getSelectedItem();
        if (producto != null) {

            ObservableList<Producto> observableListNueva = FXCollections.observableArrayList(Arrays.asList(producto));
            tableProductos.setItems(observableListNueva);

            comboBoxProv.setValue(producto.getProveedor());

            ObservableList<Integer> cantidad = FXCollections.observableArrayList();
            for (int i = 1; i <= producto.getStock(); i++) {
                cantidad.add(i);
            }
            comboBoxCant.setItems(cantidad);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error para elegir");
            alert.setContentText("Ningun producto seleccionado");
            alert.showAndWait();
        }
    }

    public void selecCantidad() {
    }

    @FXML
    void vender() {
        Producto producto = tableProductos.getSelectionModel().getSelectedItem();
        if (ControllersMethods.contieneLetras(textPrecio.getText()) || textPrecio.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error para vender");
            alert.setContentText("Precio invalido");
            alert.showAndWait();
        } else {
            if (producto != null && !comboBoxCant.getSelectionModel().isEmpty()){

                Ticket ticket = new Ticket();

                ticket.setCantidad(comboBoxCant.getSelectionModel().getSelectedItem());

                ticket.setDni(SingletonClienteClass.getInstancia().getInfo().getDni());

                Random random = new Random();
                LocalDate localDate = LocalDate.now();

                ticket.setId(random.nextInt(100000, 100000000));

                ticket.setCategoriaFiscal(SingletonClienteClass.getInstancia().getInfo().getCategoria());//Esperar que jose termine clientes
                ticket.setProducto(producto.getNombre());
                ticket.setVendedor(SingletonUsuarioClass.getInstancia().getInfo().getUsuario());
                ticket.setPrecio(Double.parseDouble(textPrecio.getText()) * ticket.getCantidad());
                ticket.setFecha(localDate.toString());

                SIngletonTicketClass.getInstancia().SetInfo(ticket);

                Popup popup = new Popup();
                popup.display();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("Seguro que quieres vender?");
                alert.setContentText("Esta acción no se puede deshacer.");

                ButtonType buttonTypeSi = new ButtonType("Sí");
                ButtonType buttonTypeNo = new ButtonType("No");

                alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeSi) {

                    producto.setStock(producto.getStock() - comboBoxCant.getValue());
                    int index = observableListProducto.indexOf(producto);


                    observableListProducto.set(index, producto);
                    tableProductos.refresh();

                    observableTicket.add(ticket);
                    textPrecio.clear();
                    comboBoxCant.getSelectionModel().clearSelection();
                    comboBoxProv.getSelectionModel().clearSelection();




                    alert.close();
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Venta");
                    alert2.setContentText("Venta realizada con exito");
                    alert2.showAndWait();

                    tableProductos.setItems(observableListProducto);
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
    }

    @FXML
    public void volver() {
        Jackson.serializar(observableListProducto, pathJsonProductos);
        Jackson.serializar(observableTicket, "src/main/java/com/tp/tp_final_lab3/Archives/tickets.json");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_VerificarCliente.fxml"));
            Stage stage = (Stage) volverButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}

