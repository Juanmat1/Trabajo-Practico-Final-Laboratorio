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

    public enum Estado{
        Activo,
        Inactivo
    }
    private Estado estado;
    private LocalDate fechaCreacion;
    private CategoriaFiscal categoria;
    private static int ultimoId;
    private static boolean primeraCarga = true;


    public Clientes() {
    }

    public Clientes(String nombre, String apellido, String dni, String cuit, String domicilio, String telefono, Estado estado) {
        super(nombre, apellido, dni);
        if(primeraCarga){
            Clientes.ultimoId = getUltimoClientessID();
            primeraCarga = false;
        }
        this.idCliente = ultimoId + 1;
        Clientes.ultimoId ++;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.estado = estado;
        this.fechaCreacion = LocalDate.now();
    }



//region getters y setters

    private static int getUltimoClientessID(){
        ArrayList<Clientes> clientes = Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/clientes.json", Clientes.class);
        if (clientes.isEmpty()){
            return 0;
        } else {
        return clientes.get(clientes.size()-1).getIdCliente();
    }}
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


    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
                ", estado=" + estado +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}



