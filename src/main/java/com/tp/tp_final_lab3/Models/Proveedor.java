package com.tp.tp_final_lab3.Models;

public class Proveedor {
    public enum Estado{
        Activo,Inactivo
    }
    private int id;
    private String nombre;
    private String razonSocial;
    private String cuit;
    private Categorias categoria;
    private Estado estado;

    public Proveedor() {
    }

    public Proveedor(String nombre, String razonSocial, String cuit, Estado estado) {
        //id autoincremental
        this.id = id;
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.cuit = cuit;

    }

    public Proveedor(int id, String nombre, String razonSocial, String cuit) {
        this.id = id;
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", cuit='" + cuit + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}

