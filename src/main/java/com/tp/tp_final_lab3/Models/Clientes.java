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
    private CategoriaFiscal categoriaFiscal;
    private EstadosPersona estadosPersona;
    private LocalDate fechaCreacion;
    private static int ultimoId;

    public Clientes() {
    }


    public Clientes(String nombre, String apellido, String dni, String cuit, String domicilio, String telefono, CategoriaFiscal categoriaFiscal, EstadosPersona estadosPersona) {
        super(nombre, apellido, dni);
        Clientes.ultimoId = getUltimoClienteID();
        this.idCliente = ultimoId + 1;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.categoriaFiscal = categoriaFiscal;
        this.estadosPersona = estadosPersona;
        this.fechaCreacion = LocalDate.now();
    }

//region getters y setters

    private static int getUltimoClienteID(){
        ArrayList<Clientes> clientes = Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/clientes.json", Usuario.class);
        return clientes.get(clientes.size()-1).getIdCliente();
    }
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

    public CategoriaFiscal getCategoriaFiscal() {
        return categoriaFiscal;
    }

    public void setCategoriaFiscal(CategoriaFiscal categoriaFiscal) {
        this.categoriaFiscal = categoriaFiscal;
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
                ", categoriaFiscal=" + categoriaFiscal +
                ", estadosPersona=" + estadosPersona +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}



