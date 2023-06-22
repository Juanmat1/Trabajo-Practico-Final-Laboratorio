package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Ticket;
import com.tp.tp_final_lab3.SingletonClasses.SIngletonTicketClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TicketController implements Initializable {
    //region FXML
    @FXML
    private Text textCantidad;
    @FXML
    private Text textCategoria;
    @FXML
    private Text textDni;
    @FXML
    private Text textId;

    @FXML
    private Text textPrecio;

    @FXML
    private Text textProducto;
    @FXML
    private Text textVendedor;
    @FXML
    private Text textFecha;

    private final LocalDate localDate = LocalDate.now();
    //endregion
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Ticket ticket = SIngletonTicketClass.getInstancia().getInfo();

        textFecha.setText(ticket.getFecha());
        textCategoria.setText("Cat.Fiscal: "+ticket.getCategoriaFiscal());
        textCantidad.setText(Integer.toString(ticket.getCantidad()));
        textDni.setText("CUIT " + ticket.getDni());
        textPrecio.setText(Double.toString(ticket.getPrecio()));
        textProducto.setText(ticket.getProducto());
        textId.setText("ID: "+ ticket.getId());
        textVendedor.setText("Vendedor: "+ticket.getVendedor());
    }


}
