package com.tp.tp_final_lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class main extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/LOGIN_importadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Importadora!!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}