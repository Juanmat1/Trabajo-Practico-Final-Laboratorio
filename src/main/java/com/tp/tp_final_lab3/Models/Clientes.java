package com.tp.tp_final_lab3.Models;

import java.io.Serializable;

public class Clientes extends Persona implements Serializable {

    private int idCliente;
    private String cuit;
    private String domicilio;
    private int telefono;
    private CategoriaFiscal categoriaFiscal;
    private EstadosPersona estadosPersona;

    public Clientes() {
    }


    public Clientes(int idCliente, String cuit, String domicilio, int telefono, CategoriaFiscal categoriaFiscal) {
        this.idCliente = idCliente;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.categoriaFiscal = categoriaFiscal;
    }

    public Clientes(String text, String textApellidoText, String textDNIText, String textCUITText, String textDomicilioText, String textTelefonoText, CategoriaFiscal selectedItem, EstadosPersona activo) {
    }

    public Clientes(String nombre, String apellido, String dni, int idCliente, String cuit, String domicilio, int telefono) {
        super(nombre, apellido, dni);
        this.idCliente = idCliente;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.telefono = telefono;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
//endregion


    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clientes clientes = (Clientes) o;
        return idCliente == clientes.idCliente && telefono == clientes.telefono && cuit.equals(clientes.cuit) && domicilio.equals(clientes.domicilio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, cuit, domicilio, telefono);
    }*/

    @Override
    public String toString() {
        return "Clientes{" +
                "idCliente=" + idCliente +
                ", cuit='" + cuit + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}



