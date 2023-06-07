package com.tp.tp_final_lab3.Models.ApiCotizaciones;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    @JsonProperty("value_avg")
    private double averageValue;

    @JsonProperty("value_sell")
    private double sellingValue;

    @JsonProperty("value_buy")
    private double buyingValue;

    // Constructor vacío requerido para la deserialización de JSON
    public Data() {
    }

    // Getters y setters
    public double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(double averageValue) {
        this.averageValue = averageValue;
    }

    public double getSellingValue() {
        return sellingValue;
    }

    public void setSellingValue(double sellingValue) {
        this.sellingValue = sellingValue;
    }

    public double getBuyingValue() {
        return buyingValue;
    }

    public void setBuyingValue(double buyingValue) {
        this.buyingValue = buyingValue;
    }


    @Override
    public String toString() {
        return "ExchangeRate{" +
                "averageValue=" + averageValue +
                ", sellingValue=" + sellingValue +
                ", buyingValue=" + buyingValue +
                '}';
    }
}
