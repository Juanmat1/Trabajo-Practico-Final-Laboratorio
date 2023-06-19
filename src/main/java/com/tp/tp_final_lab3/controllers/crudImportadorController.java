package com.tp.tp_final_lab3.controllers;


import com.tp.tp_final_lab3.Models.ApiCotizaciones.ExchangeRates;
import com.tp.tp_final_lab3.Models.Categorias;
import com.tp.tp_final_lab3.Models.Pedido;
import com.tp.tp_final_lab3.Models.Producto;
import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.SingletonClasses.SingletonUsuarioClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class crudImportadorController implements Initializable {

    private final ArrayList<Pedido> listaPedidos =
            Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json", Pedido.class);
    private final ArrayList<Producto> listaProductos =
            Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/productos.json", Producto.class);
    private final ObservableList<Pedido> observablePedido = FXCollections.observableArrayList();
    private final ObservableList<Producto> observableProducto = FXCollections.observableArrayList();




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
    private TableColumn<Pedido, Double> tableImp;

    @FXML
    private TableColumn<Pedido, String> tableName;

    @FXML
    private TableColumn<Pedido, Double> tablePrecioC;

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



        cargarArrayPedidos();
        cargarArrayProductos();
    }

    public void agregarPedido()
    {
        Pedido pedido = new Pedido();
        boolean status = false;
        if(checkCampos())
        {
            try{
                setPedido(pedido);
                status = true;
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
        if(status)
        {
            observablePedido.add(pedido);



        }
        limpiarTextBox();
        //al cerrar sesion se aplican los cambios al json, por eso quite el boton para cerrar
    }
    public void borrarPedido()
    {
        observablePedido.remove(tablePedidos.getSelectionModel().getSelectedItem());
    }
    public void cerrarSesion()
    {
        actualizarListaPedidos();
        actualizarListaProductos();

        Jackson.serializar(listaPedidos,"src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json");
        Jackson.serializar(listaProductos,"src/main/java/com/tp/tp_final_lab3/Archives/productos.json");
        //se trabaja con cache
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/LOGIN_importadora.fxml"));
            Stage stage = (Stage) buttonlogout.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void actualizar()
    {
        Pedido pedido = tablePedidos.getSelectionModel().getSelectedItem();

        if(pedido != null)
        {
            textPrecio.setText(Double.toString(pedido.getPrecioCompra()));
            textFechac.setValue(LocalDate.parse(pedido.getFechaCompra()));
            comboBoxCantidad.getSelectionModel().select(pedido.getCantidad()-1);

        }

    }
    public void modificarDatos()
    {

    }

    //region METODOS AUXILIARES

    public void limpiarTextBox()
    {
        comboBoxCat.getSelectionModel().clearSelection();
        comboBoxProduc.getSelectionModel().clearSelection();
        comboBoxCat.getSelectionModel().clearSelection();
        textPrecio.clear();
        textFechac.getEditor().clear();
    }
    public void setPedido(Pedido pedido)
    {
        pedido.setNombre(comboBoxCat.getSelectionModel().getSelectedItem());
        pedido.setCategoria(comboBoxCat.getSelectionModel().getSelectedItem());
        pedido.setCantidad(comboBoxCantidad.getSelectionModel().getSelectedItem());
        pedido.setIdProveedor(Integer.parseInt(textPrecio.getText()));
        pedido.setPrecioCompra(Integer.parseInt(textPrecio.getText()));
        pedido.setFechaCompra(textFechac.getValue().toString());
        pedido.setUsername(SingletonUsuarioClass.getInstancia().getInfo().getUsuario());


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
    }

    public void setearColumnasStock()
    {
        tableGStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableGCat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tableGProduct.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }
    public void obtenerUser()
    {
        textUser.setText("USUARIO : " +SingletonUsuarioClass.getInstancia().getInfo().getUsuario());
    }

    public void cargarArrayPedidos()
    {
        for(Pedido aux : listaPedidos)
        {
            if(aux.getUsername().equals(SingletonUsuarioClass.getInstancia().getInfo().getUsuario()))
            {
                observablePedido.add(aux);
            }
        }
        tablePedidos.setItems(observablePedido);
    }
    public void cargarArrayProductos()
    {
        for(Producto producto : listaProductos)
        {
            if(producto.getEstado().toString().equals("Activo"))
            {
                observableProducto.add(producto);
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


        for(Producto producto : observableProducto)
        {
            if(comboBoxCat.getSelectionModel().getSelectedItem().equals(producto.getCategoria()))
            {
                productosString.add(producto.getNombre());
                if(!status)
                {
                    status = true;
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
        for( Pedido pedido : listaPedidos)
        {
            if(pedido.getUsername().equals(SingletonUsuarioClass.getInstancia().getInfo().getUsuario()))
            {
                int index = listaPedidos.indexOf(pedido);

                listaPedidos.set(index,observablePedido.get(observablePedido.indexOf(pedido)));
            }
        }
    }

    public void actualizarListaProductos()
    {
        for(Producto producto : listaProductos)
        {
            int index = listaProductos.indexOf(producto);

            listaProductos.set(index,observableProducto.get(observableProducto.indexOf(producto)));
        }
    }


    //endregion





}
