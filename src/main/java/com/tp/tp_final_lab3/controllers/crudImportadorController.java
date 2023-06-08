package com.tp.tp_final_lab3.controllers;


import com.tp.tp_final_lab3.Models.ApiCotizaciones.ExchangeRates;
import com.tp.tp_final_lab3.Models.Pedido;
import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.SingletonClasses.SingletonUsuarioClass;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class crudImportadorController implements Initializable {

    ArrayList<Pedido> pedidos = Jackson.deserializarArrayListPedido("src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json");

//region FXML
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
    private ChoiceBox<?> categoriaSelec;

    @FXML
    private Text dolarBlue;

    @FXML
    private Text dolarOficial;

    @FXML
    private Text euroBlue;

    @FXML
    private Text euroOficial;

    @FXML
    private TextField idProveedor;

    @FXML
    private TableView<Pedido> tablePedidos;

    @FXML
    private TextArea textDescrip;

    @FXML
    private DatePicker textFechac;

    @FXML
    private TextField textImpuestos;

    @FXML
    private TextField textName;

    @FXML
    private TextField textPrecio;

    @FXML
    private Text textUser;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ExchangeRates exchangeRates = Jackson.obtenerDivisas();
        dolarBlue.setText("Dolar Blue: " + exchangeRates.getBlue().getBuyingValue());
        dolarOficial.setText("Dolar Oficial: " + exchangeRates.getOficial().getBuyingValue());
        euroBlue.setText("Euro Blue: " + exchangeRates.getBlueEuro().getBuyingValue());
        euroOficial.setText("Euro Oficial: " + exchangeRates.getOficialEuro().getBuyingValue());

        Usuario user = SingletonUsuarioClass.getInstancia().getInfo();
        textUser.setText("Usuario: " + user.getUsuario());
    }

    public void agregarPedido()
    {
        //agregarValidacion;

        Pedido pedido = new Pedido();

        pedido.setNombre(textName.getText());
        //pedido.setCategoria(categoriaSelec.getSelectionModel().getSelectedItem().toString());
        pedido.setIdProveedor(Integer.parseInt(textPrecio.getText()));
        pedido.setPrecioCompra(Integer.parseInt(textPrecio.getText()));
        pedido.setFechaCompra(textFechac.getValue().toString());
        pedido.setImpuestos(Integer.parseInt(textImpuestos.getText()));
        pedido.setDescripcion(textDescrip.getText());
        pedido.setUsername(SingletonUsuarioClass.getInstancia().getInfo().getUsuario());

        pedidos.add(pedido);

        limpiarTextBox();

        Jackson.serializar(pedidos,"src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json");

    }

    public void limpiarTextBox()
    {
        textName.clear();
        categoriaSelec.getSelectionModel().clearSelection();
        idProveedor.clear();
        textPrecio.clear();
        textFechac.getEditor().clear();
        textImpuestos.clear();
        textDescrip.clear();
    }

    public void borrarPedido()
    {
        //pedidos.remove(tablePedidos.getSelectionModel().getSelectedItem()); creo

        Jackson.serializar(pedidos,"src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json");

    }
    public void cerrarSesion()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/LOGIN_importadora.fxml"));
            Stage stage = (Stage) buttonlogout.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    public void loadTable()
    {


    }





}
