package com.tp.tp_final_lab3.Models;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Delta {

    private Delta() {
    }


    public static void dragScene(Stage stage, Scene scene)
     {
         final Delta dragDelta = new Delta();
         scene.setOnMousePressed(event -> {
             // Guardar las coordenadas del mouse al presionar
             dragDelta.x = stage.getX() - event.getScreenX();
             dragDelta.y = stage.getY() - event.getScreenY();
         });

         scene.setOnMouseDragged(event -> {
             // Mover el stage de acuerdo al movimiento del mouse
             stage.setX(event.getScreenX() + dragDelta.x);
             stage.setY(event.getScreenY() + dragDelta.y);
         });
     }
     double x, y;

}
