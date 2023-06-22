package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Delta;
import com.tp.tp_final_lab3.Models.Ticket;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.Services.ControllersMethods;
import com.tp.tp_final_lab3.Services.Popup;
import com.tp.tp_final_lab3.SingletonClasses.SIngletonTicketClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminTicketsController implements Initializable {
    private final String pathJson = "src/main/java/com/tp/tp_final_lab3/Archives/tickets.json";
    private ObservableList<Ticket> observableList = FXCollections.observableArrayList(Jackson.deserializarArrayList(pathJson,Ticket.class));

    @FXML
    private TableColumn<Ticket, Integer> columnCantidad;

    @FXML
    private TableColumn<Ticket, String> columnCatFiscal;

    @FXML
    private TableColumn<Ticket, String> columnDni;

    @FXML
    private TableColumn<Ticket, String> columnFecha;

    @FXML
    private TableColumn<Ticket, Integer> columnId;

    @FXML
    private TableColumn<Ticket, Double> columnPrecio;

    @FXML
    private TableColumn<Ticket, String> columnProducto;

    @FXML
    private TableColumn<Ticket, String> columnVendedor;
    @FXML
    private TableView<Ticket> tableTicket;

    @FXML
    private Button verTicketButton;

    @FXML
    private Button volverButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        columnCatFiscal.setCellValueFactory(new PropertyValueFactory<>("categoriaFiscal"));
        columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        columnVendedor.setCellValueFactory(new PropertyValueFactory<>("vendedor"));

        ControllersMethods.alinearTabla(columnCantidad);
        ControllersMethods.alinearTabla(columnDni);
        ControllersMethods.alinearTabla(columnId);
        ControllersMethods.alinearTabla(columnPrecio);
        ControllersMethods.alinearTabla(columnCatFiscal);
        ControllersMethods.alinearTabla(columnProducto);
        ControllersMethods.alinearTabla(columnFecha);
        ControllersMethods.alinearTabla(columnVendedor);

        tableTicket.setItems(observableList);
    }

    @FXML
    void verTicket(ActionEvent event) {

        Ticket ticker = tableTicket.getSelectionModel().getSelectedItem();
        if(ticker!=null){
            SIngletonTicketClass.getInstancia().SetInfo(ticker);
            Popup popup = new Popup();
            popup.display();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Ningun Ticket seleccionado");
            alert.showAndWait();
        }

    }

    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/ADMIN_Seleccion.fxml"));
            Stage stage = (Stage) volverButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

}
