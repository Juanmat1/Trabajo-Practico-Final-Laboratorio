package com.tp.tp_final_lab3.Services;

import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllersMethods {
    public static boolean checkTxtField(TextField...textFields){
        for(TextField textField : textFields){
            if(textField.getText().isEmpty()){
                return true;
            }
        }
        return false;
    }
    public static void alertaCampos(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error en los campos,reviselos");
        alert.setContentText("Algunos de los campos esta vacio");
        alert.showAndWait();
    }
    public static void limpiarTxtField(TextField...textFields){
        for(TextField textField : textFields){
            textField.clear();
        }
    }
    public static <S,T> void alinearTabla(TableColumn<S, T> column) { //centra los datos de la tableView
        column.setCellFactory(new Callback<>() {
            @Override
            public TableCell<S, T> call(TableColumn<S, T> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            setText(item.toString());
                            setAlignment(javafx.geometry.Pos.CENTER);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }
    public static boolean contieneNumeros(String... texto) {
        for (String text : texto) {
            Pattern patron = Pattern.compile("[0-9]");
            Matcher matcher = patron.matcher(text);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }
    public static boolean contieneLetras(String... texto){
        for (String text : texto) {
            Pattern patron = Pattern.compile("[a-zA-Z]");
            Matcher matcher = patron.matcher(text);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }
}
