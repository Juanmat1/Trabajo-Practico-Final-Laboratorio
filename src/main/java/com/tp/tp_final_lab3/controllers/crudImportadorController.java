package com.tp.tp_final_lab3.controllers;


import com.tp.tp_final_lab3.Models.ApiCotizaciones.ExchangeRates;
import com.tp.tp_final_lab3.Models.Categorias;
import com.tp.tp_final_lab3.Models.Pedido;
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
import java.util.ResourceBundle;

public class crudImportadorController implements Initializable {

    private ObservableList<Pedido> observableList = FXCollections.observableArrayList(Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json", Pedido.class));


//region FXML


    @FXML
    private Button buttonlogout;

    @FXML
    private ChoiceBox<Categorias> categoriaSelec;

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
    private TableColumn<Pedido, String> tableCat;

    @FXML
    private TableColumn<Pedido, String> tableFechaC;

    @FXML
    private TableColumn<Pedido, Integer> tableID;

    @FXML
    private TableColumn<Pedido, Integer> tableIdProv;

    @FXML
    private TableColumn<Pedido, Integer> tableImp;

    @FXML
    private TableColumn<Pedido, String> tableName;

    @FXML
    private TableView<Pedido> tablePedidos;

    @FXML
    private TableColumn<Pedido, Integer> tablePrecioC;


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

        categoriaSelec.getItems().addAll(Categorias.values());

        Usuario user = SingletonUsuarioClass.getInstancia().getInfo();
        textUser.setText("Usuario: " + user.getUsuario());

        tableName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableCat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tableIdProv.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        tablePrecioC.setCellValueFactory(new PropertyValueFactory<>("precioCompra"));
        tableFechaC.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        tableImp.setCellValueFactory(new PropertyValueFactory<>("impuestos"));

        tablePedidos.setItems(observableList);

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
            observableList.add(pedido);
        }

        limpiarTextBox();

        //al cerrar sesion se aplican los cambios al json, por eso quite el boton para cerrar

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
        observableList.remove(tablePedidos.getSelectionModel().getSelectedItem());
    }
    public void cerrarSesion()
    {
        Jackson.serializar(observableList,"src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json");//se trabaja con cache
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/LOGIN_importadora.fxml"));
            Stage stage = (Stage) buttonlogout.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

public void setPedido(Pedido pedido)
{
    pedido.setNombre(textName.getText());
    pedido.setCategoria(categoriaSelec.getSelectionModel().getSelectedItem().toString());
    pedido.setIdProveedor(Integer.parseInt(textPrecio.getText()));
    pedido.setPrecioCompra(Integer.parseInt(textPrecio.getText()));
    pedido.setFechaCompra(textFechac.getValue().toString());
    pedido.setImpuestos(Integer.parseInt(textImpuestos.getText()));
    pedido.setDescripcion(textDescrip.getText());
    pedido.setUsername(SingletonUsuarioClass.getInstancia().getInfo().getUsuario());
}
public boolean checkCampos()
{
    boolean status = false;

    if(textName.getText().isEmpty() || categoriaSelec.getSelectionModel().isEmpty() ||
            idProveedor.getText().isEmpty() || textPrecio.getText().isEmpty() || textFechac.getValue().toString().isEmpty()
            || textImpuestos.getText().isEmpty() || textDescrip.getText().isEmpty())
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


}
