package com.tp.tp_final_lab3.Models;

import java.io.Serializable;
import java.util.Objects;

public class Usuario extends Persona implements Serializable {

    private String usuario;
    private String contrasenia;

    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String dni, String usuario, String contrasenia) {
        super(nombre, apellido, dni);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
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

    ///agregar fecha de creacion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(this.usuario, usuario.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, contrasenia);
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}


