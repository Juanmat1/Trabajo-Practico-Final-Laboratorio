package com.tp.tp_final_lab3.Models;

import java.io.Serializable;

public class Admin extends Persona implements Serializable {

    private String usuario;
    private String contrasenia;

    public Admin() {
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
