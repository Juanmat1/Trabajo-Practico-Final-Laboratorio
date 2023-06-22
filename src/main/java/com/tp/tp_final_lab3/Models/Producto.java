package com.tp.tp_final_lab3.Models;

import com.tp.tp_final_lab3.Repository.Jackson;

import java.util.ArrayList;

public class Producto implements Comparable<Producto> {


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

    private static boolean flag = true;
    private static int ultimoId;

    public Producto() {
    }

    public Producto(String nombre, String categoria, String proveedor, int stock, Estado estado) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.stock = stock;
        this.estado = estado;

        if(flag)
        {
            Producto.ultimoId = Producto.obtenerUltimoId();
            flag= false;
        }
        this.id= Producto.ultimoId + 1;

        Producto.ultimoId++;
    }


    @Override
    public int compareTo(Producto o) {
        return Integer.compare(this.id,o.id);
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
        System.out.println(ultimoID);
        return ultimoID;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return id;
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
