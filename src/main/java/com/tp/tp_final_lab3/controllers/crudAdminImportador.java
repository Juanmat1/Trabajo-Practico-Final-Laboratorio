package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.*;
import com.tp.tp_final_lab3.Models.ApiCotizaciones.ExchangeRates;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;

public class crudAdminImportador implements Initializable,ICrud {

    private final ArrayList<Pedido> listaPedidos =
            Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json", Pedido.class);
    private final ArrayList<Proveedor> listaProveedores =
            Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/proveedores.json", Proveedor.class);
    private final ArrayList<Producto> listaProductos =
            Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/productos.json", Producto.class);
    private final ObservableList<Pedido> observablePedido = FXCollections.observableArrayList();
    private final ObservableList<Producto> observableProducto = FXCollections.observableArrayList();

    //endregion

    //region FXML

    //region TABLA STOCK
    @FXML
    private TableView<Producto> tableStock;

    @FXML
    private TableColumn<Producto, Integer> tableGStock;

    @FXML
    private TableColumn<Producto, String> tableGCat;

    @FXML
    private TableColumn<Producto, String> tableGProduct;

    //endregion

    //region TABLA PEDIDOS
    @FXML
    private TableColumn<Pedido, String> tableCat;

    @FXML
    private TableColumn<Pedido, String> tableFechaC;

    @FXML
    private TableColumn<Pedido, Integer> tableID;

    @FXML
    private TableColumn<Pedido, Integer> columnStock;

    @FXML
    private TableColumn<Pedido, Integer> tableIdProv;

    @FXML
    private TableColumn<Pedido, String> tableName;

    @FXML
    private TableColumn<Pedido, Double> tablePrecioC;
    @FXML
    private TableColumn<Pedido, String> tableUser;

    @FXML
    private TableView<Pedido> tablePedidos;

    //endregion

    //region BOTONES
    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonClear;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonlogout;

    @FXML
    private ComboBox<String> comboBoxProduc;

    @FXML
    private ComboBox<String> comboBoxCat;

    @FXML
    private ComboBox<Integer> comboBoxCantidad;

    //endregion

    //region DIVISAS
    @FXML
    private Text dolarBlue;

    @FXML
    private Text dolarOficial;

    @FXML
    private Text euroBlue;

    @FXML
    private Text euroOficial;

    //endregion

    //region TEXT

    @FXML
    private DatePicker textFechac;

    @FXML
    private TextField textPrecio;

    @FXML
    private Text textUser;

    //endregion


    //endregion
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        obtenerDivisas();
        obtenerUser();
        setCategorias();
        setComboBoxCantidad();


        setearColumnasPedidos();

        setearColumnasStock();

        textFechac.setValue(LocalDate.now());


        alinearTablas();

        cargarArrayPedidos();
        cargarArrayProductos();
    }

    //region METODOS PRINCIPALES

    @Override
    public void agregar()
    {
        if(checkCampos())
        {
            try{
                Pedido pedido = new Pedido(obtenerIDProveedor(comboBoxProduc.getSelectionModel().getSelectedItem()),comboBoxCantidad.getSelectionModel().getSelectedItem(),
                        comboBoxProduc.getSelectionModel().getSelectedItem(),comboBoxCat.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(textPrecio.getText()),textFechac.getValue().toString(),"nada", "ADMIN");

                observablePedido.add(pedido);

                modificarStock(pedido.getNombre(), pedido.getCategoria(), pedido.getCantidad());

                tableStock.refresh();


            }catch (Exception e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en los campos,reviselos");
                alert.setContentText("Algunos de los campos es " +
                        "incorrecto revise de no poner letras en los campos con numeros");
                alert.showAndWait();
                e.printStackTrace();
            }
        }

        limpiar();
        //al cerrar sesion se aplican los cambios al json, por eso quite el boton para cerrar
    }
    @Override
    public void borrar()
    {
        observablePedido.remove(tablePedidos.getSelectionModel().getSelectedItem());
        actualizarListaPedidos();
        Jackson.serializar(listaPedidos,"src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json");
        tablePedidos.getItems().clear();
        cargarArrayPedidos();
        Pedido.ultimoId--;
    }
    public void cerrarSesion()
    {
        actualizarListaPedidos();
        actualizarListaProductos();

        Jackson.serializar(listaPedidos,"src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json");
        Jackson.serializar(listaProductos,"src/main/java/com/tp/tp_final_lab3/Archives/productos.json");
        //se trabaja con cache
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/ADMIN_Seleccion.fxml"));
            Stage stage = (Stage) buttonlogout.getScene().getWindow();

            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Override
    public void actualizar()
    {
        Pedido pedido = tablePedidos.getSelectionModel().getSelectedItem();


        textPrecio.setText(Integer.toString(pedido.getPrecioCompra()));
        textFechac.setValue(LocalDate.parse(pedido.getFechaCompra()));
        comboBoxCat.getSelectionModel().select(obtenerIndexCategoria(pedido.getCategoria()));
        comboBoxCantidad.getSelectionModel().select(pedido.getCantidad()-1);
        comboBoxProduc.getSelectionModel().select(obtenerIndexProductos(pedido.getNombre()));
        buttonUpdate.setText("Guardar");

        buttonUpdate.setOnAction(event ->{

            modificarDatos( pedido);
        });

    }
    public void modificarDatos(Pedido pedido)
    {
        if(checkCampos())
        {
            pedido.setNombre(comboBoxProduc.getSelectionModel().getSelectedItem());
            pedido.setCategoria(comboBoxCat.getSelectionModel().getSelectedItem());
            pedido.setCantidad(comboBoxCantidad.getSelectionModel().getSelectedItem());
            pedido.setPrecioCompra(Integer.parseInt(textPrecio.getText()));
            pedido.setFechaCompra(textFechac.getValue().toString());

            observablePedido.set(observablePedido.indexOf(pedido),pedido);
            limpiar();


            buttonUpdate.setText("Actualizar");
            buttonUpdate.setOnAction(event -> {
                actualizar();
            });
        }
    }

    //endregion

    //region METODOS AUXILIARES

    @Override
    public void limpiar()
    {
        comboBoxCat.getSelectionModel().clearSelection();
        comboBoxProduc.getSelectionModel().clearSelection();
        comboBoxCantidad.getSelectionModel().clearSelection();
        textPrecio.clear();
        //textFechac.getEditor().clear();
        textFechac.setValue(LocalDate.now());
    }
    public boolean checkCampos()
    {
        boolean status = false;

        if( textPrecio.getText().isEmpty() || textFechac.getValue().toString().isEmpty()
                || comboBoxProduc.getSelectionModel().isEmpty() ||
                comboBoxCat.getSelectionModel().isEmpty()|| comboBoxCat.getSelectionModel().isEmpty())
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

    public void obtenerDivisas()
    {
        ExchangeRates exchangeRates = Jackson.obtenerDivisas();
        dolarBlue.setText("Dolar Blue: " + exchangeRates.getBlue().getBuyingValue());
        dolarOficial.setText("Dolar Oficial: " + exchangeRates.getOficial().getBuyingValue());
        euroBlue.setText("Euro Blue: " + exchangeRates.getBlueEuro().getBuyingValue());
        euroOficial.setText("Euro Oficial: " + exchangeRates.getOficialEuro().getBuyingValue());
    }

    public void setearColumnasPedidos()
    {
        tableName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableCat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tableIdProv.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        tablePrecioC.setCellValueFactory(new PropertyValueFactory<>("precioCompra"));
        tableFechaC.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        tableID.setCellValueFactory(new PropertyValueFactory<>("idOrdenDcompra"));
        columnStock.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tableUser.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    public void setearColumnasStock()
    {
        tableGStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableGCat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tableGProduct.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }
    public void obtenerUser()
    {
        textUser.setText("ADMIN");
    }

    public void cargarArrayPedidos()
    {
        Iterator<Pedido> iterator = listaPedidos.iterator();

        while(iterator.hasNext())
        {
            Pedido pedido = iterator.next();


                observablePedido.add(pedido);
                iterator.remove();

        }
        tablePedidos.setItems(observablePedido);

    }
    public void cargarArrayProductos() {

        Iterator<Producto> iterator = listaProductos.iterator();

        while(iterator.hasNext())
        {
            Producto producto = iterator.next();

            if(producto.getEstado().toString().equals("Activo"))
            {
                observableProducto.add(producto);
                iterator.remove();
            }
        }
        tableStock.setItems(observableProducto);

    }

    public void setCategorias()
    {
        ObservableList<String> categoriasString = FXCollections.observableArrayList();

        for(Categorias categoria : Categorias.values())
        {
            categoriasString.add(categoria.toString());
        }
        comboBoxCat.setItems(categoriasString);
    }

    public void setProductos()
    {
        ObservableList<String> productosString = FXCollections.observableArrayList();
        boolean status = false;

        if(!comboBoxCat.getSelectionModel().isEmpty())
        {

            for (Producto producto : observableProducto) {

                if (comboBoxCat.getSelectionModel().getSelectedItem().equals(producto.getCategoria())) {
                    productosString.add(producto.getNombre());
                    if (!status) {
                        status = true;
                    }
                }
            }
        }
        if(!status)
        {
            comboBoxProduc.setPromptText("0 Productos");
        }
        else
        {
            comboBoxProduc.setItems(productosString);
            comboBoxProduc.setPromptText("Categoria");
        }
    }

    public void setComboBoxCantidad()
    {
        ObservableList<Integer> cantidad = FXCollections.observableArrayList();

        for (int i = 1; i <= 30; i++) {
            cantidad.add(i);
        }
        comboBoxCantidad.setItems(cantidad);
    }

    public void actualizarListaPedidos()
    {
        listaPedidos.addAll(observablePedido);

        Collections.sort(listaPedidos);
    }

    public void actualizarListaProductos()
    {
        listaProductos.addAll(observableProducto);

        Collections.sort(listaProductos);
    }

    public void modificarStock(String nombre,String categoria,int cantidad)
    {
        for(Producto producto : observableProducto)
        {
            if(nombre.equals(producto.getNombre()) && categoria.equals(producto.getCategoria()))
            {
                producto.setStock(producto.getStock()+cantidad);
                break;
            }
        }
    }

    public int obtenerIDProveedor(String productoName)
    {
        int idProv = 0;
        String proveedorName = "";

        for( Producto producto : observableProducto)
        {
            if(producto.getNombre().equals(productoName))
            {
                proveedorName = producto.getProveedor();
                break;
            }

        }

        for(Proveedor proveedor : listaProveedores)
        {
            if(proveedor.getRazonSocial().equals(proveedorName))
            {
                idProv = proveedor.getId();
                break;
            }
        }
        System.out.println(idProv);
        return idProv;
    }

    public int obtenerIndexCategoria(String categoria)
    {
        int index = 0;

        for(String string : comboBoxCat.getItems())
        {

            if(string.equals(categoria))
            {
                break;
            }
            index++;
        }
        return  index;
    }

    public int obtenerIndexProductos(String producto)
    {
        int index = 0;

        for(String string : comboBoxProduc.getItems())
        {
            if(string.equals(producto))
            {
                break;
            }
            index++;
        }

        return index;
    }

    public void alinearTablas()
    {
        ControllersMethods.alinearTabla(columnStock);
        ControllersMethods.alinearTabla(tableCat);
        ControllersMethods.alinearTabla(tablePrecioC);
        ControllersMethods.alinearTabla(tableID);
        ControllersMethods.alinearTabla(tableIdProv);
        ControllersMethods.alinearTabla(tableName);
        ControllersMethods.alinearTabla(tableFechaC);
        ControllersMethods.alinearTabla(tableUser);
        ControllersMethods.alinearTabla(tableGCat);
        ControllersMethods.alinearTabla(tableGStock);
        ControllersMethods.alinearTabla(tableGProduct);
    }


    //endregion


}
