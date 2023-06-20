package com.tp.tp_final_lab3.Models;

import com.tp.tp_final_lab3.Repository.Jackson;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Clientes extends Persona implements Serializable {

    private int idCliente;
    private String cuit;
    private String domicilio;
    private String telefono;
    private LocalDate fechaCreacion;
    private EstadosPersona estado;
    private CategoriaFiscal categoria;

    private static int ultimoId;

    public Clientes(String text, String textApellidoText, String textDNIText, String textCUITText, String textDomicilioText, String textTelefonoText, String textCategoriaText, EstadosPersona activo) {
    }
    public Clientes(int idCliente, String cuit, String domicilio, String telefono, LocalDate fechaCreacion, EstadosPersona estado, CategoriaFiscal categoria) {
        this.idCliente = idCliente;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.categoria = categoria;
    }



    public Clientes(String nombre, String apellido, String dni, int idCliente, String cuit, String domicilio, String telefono, EstadosPersona estado, CategoriaFiscal categoria) {
        super(nombre, apellido, dni);
        Clientes.ultimoId=getUltimoUsersID();
        this.idCliente = ultimoId + 1;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.estado = estado;
        this.categoria= categoria;
        this.fechaCreacion = LocalDate.now();
    }

//region getters y setters

    private static int getUltimoUsersID(){
        ArrayList<Clientes> clientes = Jackson.deserializarArrayList("src/main/java/com/tp/tp_final_lab3/Archives/clientes.json", Usuario.class);
        return clientes.get(clientes.size()-1).getIdCliente();
    }

    public CategoriaFiscal getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaFiscal categoria) {
        this.categoria = categoria;
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

    public EstadosPersona getEstado() {
        return estado;
    }

    public void setEstado(EstadosPersona estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public static int getUltimoId() {
        return ultimoId;
    }

    public static void setUltimoId(int ultimoId) {
        Clientes.ultimoId = ultimoId;
    }
    //endregion


    @Override
    public String toString() {
        return "Clientes{" +
                "idCliente=" + idCliente +
                ", cuit='" + cuit + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", telefono=" + telefono +
                ", fechaCreacion=" + fechaCreacion +
                ", estado=" + estado +
                ", categoria=" + categoria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clientes clientes)) return false;
        return idCliente == clientes.idCliente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }
}



