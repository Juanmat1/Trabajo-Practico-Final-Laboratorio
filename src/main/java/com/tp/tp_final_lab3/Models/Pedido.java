package com.tp.tp_final_lab3.Models;

import com.tp.tp_final_lab3.Repository.Jackson;

import java.util.ArrayList;

public class Pedido implements Comparable<Pedido> {

    private int idOrdenDcompra;
    private int idProveedor;
    private int cantidad;
    private String nombre;
    private String categoria;
    private int precioCompra;
    private String fechaCompra;
    private String descripcion;
    private String username;


    public Pedido( int idProveedor, int cantidad,
                  String nombre, String categoria, int precioCompra, String fechaCompra,
                  String descripcion, String username)
    {
        int ultimoID = ultimoID();
        this.idOrdenDcompra = ultimoID + 1;
        this.idProveedor = idProveedor;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioCompra = precioCompra;
        this.fechaCompra = fechaCompra;
        this.descripcion = descripcion;
        this.username = username;
    }

    public Pedido() {
    }

    @Override
    public int compareTo(Pedido o) {

        return Integer.compare(this.idOrdenDcompra,o.idOrdenDcompra);
    }

    //region GyS
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

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdOrdenDcompra() {
        return idOrdenDcompra;
    }

    public void setIdOrdenDcompra(int idOrdenDcompra) {
        this.idOrdenDcompra = idOrdenDcompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        return idOrdenDcompra == pedido.idOrdenDcompra;
    }

    @Override
    public int hashCode() {
        return idOrdenDcompra;
    }



    private int ultimoID()
    {
        int ultimoId;

        ArrayList<Pedido> pedidos = Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/pedidos.json",Pedido.class);

        ultimoId = pedidos.get(pedidos.size()-1).idOrdenDcompra;

        return ultimoId;
    }
}

