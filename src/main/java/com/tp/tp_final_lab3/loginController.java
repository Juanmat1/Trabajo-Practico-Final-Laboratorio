package com.tp.tp_final_lab3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class loginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button createButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField textPassword;

    @FXML
    private TextField textUser;

    @FXML
    public void loginAction(ActionEvent actionEvent)
    {
       if(textUser.getText().equals("admin") && textPassword.getText().equals("admin"))
       {
           try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/ADMIN_Seleccion.fxml"));
               Stage stage = (Stage) loginButton.getScene().getWindow();
               Scene scene = new Scene(loader.load());
               stage.setScene(scene);
           }catch (IOException io) {
               io.printStackTrace();
           }
       }else
       {
           try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp/tp_final_lab3/Views/CRUD_Importador.fxml"));
               Stage stage = (Stage) loginButton.getScene().getWindow();
               Scene scene = new Scene(loader.load());
               stage.setScene(scene);
           }catch (IOException io) {
               io.printStackTrace();
           }
       }
    }

    @FXML
    public void creteAccount()
    {



    }

    @FXML
    public void helpAction()
    {}


}
