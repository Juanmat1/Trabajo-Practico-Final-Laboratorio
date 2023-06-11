package com.tp.tp_final_lab3.controllers;

import com.tp.tp_final_lab3.Models.Usuario;
import com.tp.tp_final_lab3.Repository.Jackson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class createUserController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    ArrayList<Usuario> users;



    @FXML
    private Button loginButton;

    @FXML
    private TextField textApellido;

    @FXML
    private TextField textNombre;

    @FXML
    private PasswordField textPassword;

    @FXML
    private TextField textUser;

    @FXML
    private PasswordField textValidatePass;

    @FXML
    private TextField textDNI;

    public void crearCuenta(){

        users = Jackson.deserializarArrayListUser("src/main/java/com/tp/tp_final_lab3/Archives/usuarios.json");

        Usuario user = new Usuario(textNombre.getText(),textApellido.getText(),textDNI.getText(),textUser.getText(),textPassword.getText(), Usuario.Estado.Activo);

        if(textPassword.getText().isEmpty() || textValidatePass.getText().isEmpty() || textUser.getText().isEmpty()
                || textNombre.getText().isEmpty() || textApellido.getText().isEmpty() || textDNI.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Login");
            alert.setHeaderText("Complete todos los campos");
            alert.showAndWait();
        }

        else if(!textPassword.getText().equals(textValidatePass.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Login");
            alert.setHeaderText("Las contraseñas no coinciden");
            alert.showAndWait();
        }
        else if(users.contains(user)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Login");
            alert.setHeaderText("El usuario ya existe");
            alert.showAndWait();
        }
        else{
            users.add(user);

            Jackson.serializar(users,"src/main/java/com/tp/tp_final_lab3/Archives/usuarios.json");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cuenta creada");
            alert.setHeaderText("Su cuenta ha sido creada con exito");
            alert.showAndWait();
        }

    }

    public void irLogin()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/LOGIN_importadora.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }



}
