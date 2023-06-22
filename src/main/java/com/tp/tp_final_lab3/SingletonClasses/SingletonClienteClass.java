package com.tp.tp_final_lab3.SingletonClasses;

import com.tp.tp_final_lab3.Models.Clientes;


public class SingletonClienteClass {

    private static SingletonClienteClass instancia;

    private static Clientes clienteData;

    private SingletonClienteClass(){}

    public static SingletonClienteClass getInstancia() {
        if (instancia == null) {
            instancia = new SingletonClienteClass();
        }
        return instancia;
    }
    public void SetInfo(Clientes user)
    {
        clienteData = user;
    }
    public Clientes getInfo()
    {
        return clienteData;
    }

}
