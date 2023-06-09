package com.tp.tp_final_lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
//comentario Juanma

public class main extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/LOGIN_importadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        String imageUrl = Objects.requireNonNull(getClass().getResource("/Images/UTNLogo.png")).toExternalForm();

            Image image = new Image(imageUrl);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(image);
        stage.setTitle("TP Final 3");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void launch(String[] args) {
        launch();
    }
}