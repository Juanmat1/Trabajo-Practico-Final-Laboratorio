package com.tp.tp_final_lab3.Models;

import com.tp.tp_final_lab3.Repository.Jackson;

import java.util.ArrayList;

public class Proveedor {
    public enum Estado{
        Activo,Inactivo
    }
    private int id;
    private String nombre;
    private String razonSocial;
    private String cuit;
    private Estado estado;
    private static int ultimoId;
    private static boolean primeraCarga = true;
    public Proveedor() {
    }

    public Proveedor(String nombre, String razonSocial, String cuit, Estado estado) {
        this.nombre = nombre;
        if(primeraCarga){
            Proveedor.ultimoId = getUltimoProveedoresID();
            primeraCarga = false;
        }
        this.id = ultimoId + 1;
        Proveedor.ultimoId ++;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.estado = estado;
    }
    private static int getUltimoProveedoresID(){
        ArrayList<Proveedor> proveedors = Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/proveedores.json", Proveedor.class);
        return proveedors.get(proveedors.size()-1).getId();
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
                ", estado=" + estado +
                '}';
    }
}

