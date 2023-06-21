package com.tp.tp_final_lab3.Models;

import com.tp.tp_final_lab3.Repository.Jackson;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Clientes extends Persona implements Serializable {

    private int idCliente;
    private String cuit;
    private String domicilio;
    private String telefono;
    private EstadosPersona estadosPersona;
    private LocalDate fechaCreacion;
    private CategoriaFiscal categoria;


    public Clientes() {
    }


    public Clientes(String nombre, String apellido, String dni, String cuit, String domicilio, String telefono, EstadosPersona estadosPersona) {
        super(nombre, apellido, dni);
        this.idCliente = idCliente;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.estadosPersona = estadosPersona;
        this.fechaCreacion = LocalDate.now();
    }

    public Clientes(int i, String text, String text1, String text2, String text3, String text4, String text5, EstadosPersona activo) {
    }

//region getters y setters


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public EstadosPersona getEstadosPersona() {
        return estadosPersona;
    }

    public void setEstadosPersona(EstadosPersona estadosPersona) {
        this.estadosPersona = estadosPersona;
    }
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    //endregion


    @Override
    public String toString() {
        return "Clientes{" +
                "idCliente=" + idCliente +
                ", cuit='" + cuit + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estadosPersona=" + estadosPersona +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}



