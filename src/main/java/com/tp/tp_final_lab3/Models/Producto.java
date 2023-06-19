package com.tp.tp_final_lab3.Models;

import com.tp.tp_final_lab3.Repository.Jackson;

import java.util.ArrayList;
import java.util.Objects;

public class Producto {


    public enum Estado{
        Activo,
        Inactivo
    }

    private int id;
    private String nombre;
    private String categoria;
    private String proveedor;
    private int stock;
    private Estado estado;

    public Producto() {
    }

    public Producto(String nombre, String categoria, String proveedor, int stock, Estado estado) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.stock = stock;
        this.estado = estado;
        int ultimoId = obtenerUltimoId();
        this.id=ultimoId+1;
    }

    //region GYS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    //endregion

    private static int obtenerUltimoId()
    {
        int ultimoID;

        ArrayList<Producto> arrayList =
                Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/productos.json", Producto.class);

        ultimoID = arrayList.get(arrayList.size()-1).id;
        return ultimoID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        if (!Objects.equals(nombre, producto.nombre)) return false;
        if (!Objects.equals(categoria, producto.categoria)) return false;
        return Objects.equals(proveedor, producto.proveedor);
    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (proveedor != null ? proveedor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "stock=" + stock +
                ", proveedor='" + proveedor + '\'' +
                ", nombre='" + nombre + '\'' +
                ", id=" + id +
                ", estado='" + estado + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
