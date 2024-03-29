package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Admin;
import com.tp.tp_final_lab3.Models.Delta;
import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import com.tp.tp_final_lab3.SingletonClasses.SingletonAdminClass;
import com.tp.tp_final_lab3.SingletonClasses.SingletonUsuarioClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class loginController implements Initializable {


    ArrayList<Usuario> users;
    ArrayList<Admin> admins;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private Button loginButton;
    @FXML
    private PasswordField textPassword;
    @FXML
    private TextField textUser;
    @FXML
    private Button buttonClose;

    @FXML
    public void loginAction() {



            File file1 = new File("src/main/resources/Sound/miguel-oharris-spider-man-2099.mp3");


            Media media = new Media(file1.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();


        String pathJsonUsers = "src/main/java/com/tp/tp_final_lab3/Archives/usuarios.json";
        String pathJsonAdmins = "src/main/java/com/tp/tp_final_lab3/Archives/administradores.json";

        if (textUser.getText().isEmpty() || textPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Login");
            alert.setHeaderText("Complete ambos campos");
            alert.showAndWait();
        } else {

            admins = Jackson.deserializarArrayList(pathJsonAdmins, Admin.class);
            Admin admin = new Admin(textUser.getText(), textPassword.getText());

            if (admins.contains(admin)) {
                try {
                    SingletonAdminClass.getInstancia().SetInfo(admin);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/ADMIN_Seleccion.fxml"));
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    Scene scene = new Scene(loader.load());


                    scene.setFill(Color.TRANSPARENT);
                    Delta.dragScene(stage,scene);

                    stage.setScene(scene);
                } catch (IOException io) {
                    io.printStackTrace();
                }
            } else {

                users = Jackson.deserializarArrayList(pathJsonUsers, Usuario.class);
                Usuario user = new Usuario(textUser.getText(), textPassword.getText());


                if (users.contains(user)) {

                    SingletonUsuarioClass.getInstancia().SetInfo(user);

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_Seleccion.fxml"));
                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        Scene scene = new Scene(loader.load());

                        scene.setFill(Color.TRANSPARENT);
                        Delta.dragScene(stage,scene);

                        stage.setScene(scene);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Login");
                    alert.setHeaderText("Usuario o contrasenia Incorrectos");
                    alert.showAndWait();
                }

            }


        }


    }
    @FXML
    public void createAccount() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/USUARIO_creacion.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            scene.setFill(Color.TRANSPARENT);
            Delta.dragScene(stage,scene);

            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void closeAction(){

        Stage stage = (Stage) buttonClose.getScene().getWindow();

        stage.close();


    }
}
