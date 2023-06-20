package com.tp.tp_final_lab3.Services;

import com.tp.tp_final_lab3.Models.Categorias;
import com.tp.tp_final_lab3.Models.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ConsultaVenta {

    public static void filtrarProducto(Producto producto, TableView tableProductos, ObservableList<Producto> productos) {

        String categoria = producto.getCategoria();
        String proveedor = producto.getProveedor();

        ArrayList<Producto> productosFiltrados = new ArrayList<>();

        for (Producto p : productos) {
            boolean categoriaCoincide = (categoria == null) || categoria.equals(p.getCategoria());
            boolean proveedorCoincide = (proveedor == null) || proveedor.equals(p.getProveedor());

            if (categoriaCoincide && proveedorCoincide) {
                productosFiltrados.add(p);
            }
        }
        tableProductos.setItems(FXCollections.observableArrayList(productosFiltrados));
    }
}
