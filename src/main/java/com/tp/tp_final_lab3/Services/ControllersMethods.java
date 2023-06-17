package com.tp.tp_final_lab3.Services;

import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class ControllersMethods {
    public static boolean checkCampos(TextField... textFields)
    {
        boolean status = false;

        if(!verificar(textFields))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en los campos,reviselos");
            alert.setContentText("Algunos de los campos esta vacio");
            alert.showAndWait();
        }
        else {
            status = true;
        }
        return status;
    }
    public static boolean verificar(TextField...textFields){
        for(TextField textField : textFields){
            if(textField.getText().isEmpty()){
                return false;
            }
        }
        return true;
    }
    public static void limpiar(TextField...textFields){
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
}
