package com.tp.tp_final_lab3.Models;

import java.io.Serializable;
import java.util.Objects;

public class Admin extends Persona implements Serializable {

    private String usuario;
    private String contrasenia;

    public Admin(String nombre, String apellido, String dni, String usuario, String contrasenia) {
        super(nombre, apellido, dni);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Admin() {
    }

    public Admin(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(usuario, admin.usuario) && Objects.equals(contrasenia, admin.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, contrasenia);
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
}
