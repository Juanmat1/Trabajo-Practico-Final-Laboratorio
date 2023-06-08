package com.tp.tp_final_lab3.Models;

public class Pedido {
    private String nombre;
    private String categoria;
    private int idProveedor;
    private int precioCompra;
    private String fechaCompra;
    private double impuestos;
    private String descripcion;
    private String username;

    public Pedido(String nombre, String categoria, int idProveedor, int precioCompra, String fechaCompra, double impuestos, String descripcion, String username) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.idProveedor = idProveedor;
        this.precioCompra = precioCompra;
        this.fechaCompra = fechaCompra;
        this.impuestos = impuestos;
        this.descripcion = descripcion;
        this.username = username;
    }

    public Pedido() {
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

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
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

    //endregion
}
