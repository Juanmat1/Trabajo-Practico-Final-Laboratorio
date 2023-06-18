package com.tp.tp_final_lab3.controllers;

public interface IAdminCrud <T>{
    void agregar();
    void actualizar();
    void modificar(T objeto);
    void borrar();
    void limpiar();
}
