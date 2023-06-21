package com.tp.tp_final_lab3;


import com.tp.tp_final_lab3.Models.Delta;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import java.util.Objects;
//comentario Juanma
///comentario Diego

public class main extends Application {



    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/LOGIN_importadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        String imageUrl = Objects.requireNonNull(getClass().getResource("/Images/UTNLogo.png")).toExternalForm();

            Image image = new Image(imageUrl);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(image);
        stage.setTitle("TP Final 3");
        stage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);
        Delta.dragScene(stage,scene);

        stage.setResizable(false);

        stage.show();
    }




    public static void launch(String[] args) {
        launch();
    }



}