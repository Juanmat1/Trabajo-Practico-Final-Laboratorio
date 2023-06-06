package com.tp.tp_final_lab3.SingletonClasses;

import com.tp.tp_final_lab3.Models.Usuario;

public class SingletonUsuarioClass {

    private static SingletonUsuarioClass instancia;

    private static Usuario userData;

    private SingletonUsuarioClass(){}

    public static SingletonUsuarioClass getInstancia() {
        if (instancia == null) {
            instancia = new SingletonUsuarioClass();
        }
        return instancia;
    }

    public void SetInfo(Usuario user)
    {
        userData = user;
    }
    public Usuario getInfo()
    {
        return userData;
    }

}
