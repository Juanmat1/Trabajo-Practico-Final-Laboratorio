package com.tp.tp_final_lab3.Models;

public class Proveedor {
    private int id;
    private String nombre;
    private String razonSocial;
    private String cuit;
    private Categorias categoria;

    public Proveedor() {
    }

    public Proveedor(int id, String nombre, String razonSocial, String cuit, Categorias categoria) {
        this.id = id;
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.categoria = categoria;
    }

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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }
}

