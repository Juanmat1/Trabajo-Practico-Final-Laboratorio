package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Dolarsito;
import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.SingletonClasses.SingletonUsuarioClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class crudImportadorController implements Initializable {

    @FXML
    private Text textDolar;

    @FXML
    private Text textUser;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dolarsito dolar = Jackson.obtenerDolar();
        textDolar.setText("Dolar: " + dolar.getPageProps().getRealTimeQuotations().getQuotations().getMep().getBuy());

        Usuario user = SingletonUsuarioClass.getInstancia().getInfo();
        textUser.setText("Usuario: " + user.getUsuario());
    }





}
