package com.tp.tp_final_lab3.Servicios;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ControllersMethods {
    public static <S,T> void setTableCellAlignment(TableColumn<S, T> column) { //centra los datos de la tableView
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
