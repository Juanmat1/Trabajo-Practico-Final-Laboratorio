package com.tp.tp_final_lab3.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dolarsito {

    private PageProps pageProps;

    public PageProps getPageProps() {
        return pageProps;
    }

    public void setPageProps(PageProps pageProps) {
        this.pageProps = pageProps;
    }
    public static class PageProps {
        private String currency;
        private long timestamp;
        private RealTimeQuotations realTimeQuotations;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public RealTimeQuotations getRealTimeQuotations() {
            return realTimeQuotations;
        }

        public void setRealTimeQuotations(RealTimeQuotations realTimeQuotations) {
            this.realTimeQuotations = realTimeQuotations;
        }
    }

    public static class RealTimeQuotations {
        private Quotations quotations;

        public Quotations getQuotations() {
            return quotations;
        }

        public void setQuotations(Quotations quotations) {
            this.quotations = quotations;
        }
    }

    //Elementos del JSON, con los tipos de dolares
    public static class Quotations {
        private Quotation oficial;
        private Quotation ahorro;
        private Quotation tarjeta;
        private Quotation qatar;
        private Quotation netflix;
        private Quotation informal;
        private Quotation mayorista;
        private Quotation ccl;
        private Quotation mep;
        private Quotation letsBit;
        private Quotation ledesMep;
        private Quotation ledesCcl;
        private Quotation tiendadolar;

       //region Getters and Setters
        public Quotation getTiendadolar() {
            return tiendadolar;
        }

        public void setTiendadolar(Quotation tiendadolar) {
            this.tiendadolar = tiendadolar;
        }

        public Quotation getTarjeta() {
            return tarjeta;
        }

        public void setTarjeta(Quotation tarjeta) {
            this.tarjeta = tarjeta;
        }

        public Quotation getQatar() {
            return qatar;
        }

        public void setQatar(Quotation qatar) {
            this.qatar = qatar;
        }

        public Quotation getNetflix() {
            return netflix;
        }

        public void setNetflix(Quotation netflix) {
            this.netflix = netflix;
        }

        public Quotation getInformal() {
            return informal;
        }

        public void setInformal(Quotation informal) {
            this.informal = informal;
        }

        public Quotation getMayorista() {
            return mayorista;
        }

        public void setMayorista(Quotation mayorista) {
            this.mayorista = mayorista;
        }

        public Quotation getCcl() {
            return ccl;
        }

        public void setCcl(Quotation ccl) {
            this.ccl = ccl;
        }

        public Quotation getMep() {
            return mep;
        }

        public void setMep(Quotation mep) {
            this.mep = mep;
        }

        public Quotation getLetsBit() {
            return letsBit;
        }

        public void setLetsBit(Quotation letsBit) {
            this.letsBit = letsBit;
        }

        public Quotation getLedesMep() {
            return ledesMep;
        }

        public void setLedesMep(Quotation ledesMep) {
            this.ledesMep = ledesMep;
        }

        public Quotation getLedesCcl() {
            return ledesCcl;
        }

        public void setLedesCcl(Quotation ledesCcl) {
            this.ledesCcl = ledesCcl;
        }

        public Quotation getOficial() {
            return oficial;
        }

        public void setOficial(Quotation oficial) {
            this.oficial = oficial;
        }

        public Quotation getAhorro() {
            return ahorro;
        }

        public void setAhorro(Quotation ahorro) {
            this.ahorro = ahorro;
        }
        //endregion
    }

    //atributos de cada tipo de dolar
    public static class Quotation {
        private String name;
        private String date;
        private String buy;
        private String sell;
        private String variation;
        private String spread;
        private String volumen;

        // Anotación para asignar el nombre del campo en el JSON al atributo correspondiente en la clase
        @JsonProperty("name")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("date")
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @JsonProperty("buy")
        public String getBuy() {
            return buy;
        }

        public void setBuy(String buy) {
            this.buy = buy;
        }

        @JsonProperty("sell")
        public String getSell() {
            return sell;
        }

        public void setSell(String sell) {
            this.sell = sell;
        }

        @JsonProperty("variation")
        public String getVariation() {
            return variation;
        }

        public void setVariation(String variation) {
            this.variation = variation;
        }

        @JsonProperty("spread")
        public String getSpread() {
            return spread;
        }

        public void setSpread(String spread) {
            this.spread = spread;
        }

        @JsonProperty("volumen")
        public String getVolumen() {
            return volumen;
        }

        public void setVolumen(String volumen) {
            this.volumen = volumen;
        }

        // Agrega aquí los demás getters y setters para los campos adicionales
    }

}
