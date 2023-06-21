package com.tp.tp_final_lab3.Models;

public enum CategoriaFiscal {
    MONOTRIBUTISTA("Monotributista"),RESPONSABLE_INSCRIPTO("Responsable Inscripto"),CONSUMIDOR_FINAL ("Consumidor final"), EXENTO ("Exento");
    private final String descripcion;
    private CategoriaFiscal(String descripcion) {
        this.descripcion=descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}


