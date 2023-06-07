package com.tp.tp_final_lab3.Models.ApiCotizaciones;

import com.fasterxml.jackson.annotation.JsonProperty;

// Clase que representa el JSON de respuesta de la API, con los datos de las cotizaciones, y la fecha de actualización
// de Bluelytics.

public class ExchangeRates {
    @JsonProperty("oficial")
    private Data oficial;

    @JsonProperty("blue")
    private Data blue;

    @JsonProperty("oficial_euro")
    private Data oficialEuro;

    @JsonProperty("blue_euro")
    private Data blueEuro;

    @JsonProperty("last_update")
    private String lastUpdate;

    // Constructor vacío requerido para la deserialización de JSON
    public ExchangeRates() {
    }

    // Getters y setters
    public Data getOficial() {
        return oficial;
    }

    public void setOficial(Data oficial) {
        this.oficial = oficial;
    }

    public Data getBlue() {
        return blue;
    }

    public void setBlue(Data blue) {
        this.blue = blue;
    }

    public Data getOficialEuro() {
        return oficialEuro;
    }

    public void setOficialEuro(Data oficialEuro) {
        this.oficialEuro = oficialEuro;
    }

    public Data getBlueEuro() {
        return blueEuro;
    }

    public void setBlueEuro(Data blueEuro) {
        this.blueEuro = blueEuro;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "oficial=" + oficial +
                ", blue=" + blue +
                ", oficialEuro=" + oficialEuro +
                ", blueEuro=" + blueEuro +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}

