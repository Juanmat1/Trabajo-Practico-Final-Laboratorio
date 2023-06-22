package com.tp.tp_final_lab3.Services;


import javafx.fxml.FXMLLoader;


import javafx.scene.Scene;

import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;


public class Popup{

    public Popup() {
    }
    public void display()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/ticketVenta.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(loader.load());

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("This is a pop up window");


            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}