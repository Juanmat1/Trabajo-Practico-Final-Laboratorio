package com.tp.tp_final_lab3.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Usuario extends Persona implements Serializable {
    public enum Estado{
        Activo,
        Inactivo
    }
    private String usuario;
    private String contrasenia;
    private int id;
    private Estado estado;
    private LocalDate fechaCreacion;
    public static int ultimoId;

    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String dni, String usuario, String contrasenia,Estado estado) {
        super(nombre, apellido, dni);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.id = ultimoId + 1;
        this.estado = estado;
        this.fechaCreacion = LocalDate.now();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public static int getUltimoId() {
        return ultimoId;
    }

    public static void setUltimoId(int ultimoId) {
        Usuario.ultimoId = ultimoId;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(usuario, usuario1.usuario) && Objects.equals(contrasenia, usuario1.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, contrasenia);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", id=" + id +
                ", estado=" + estado +
                ", fechaCreacion=" + fechaCreacion +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}


