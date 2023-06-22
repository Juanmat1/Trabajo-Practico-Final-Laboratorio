package com.tp.tp_final_lab3.Models;

public class Ticket {

    private int cantidad;
    private String categoriaFiscal;
    private String dni;
    private int id;
    private double precio;
    private String vendedor;
    private String producto;

    private String fecha;

    public Ticket(int cantidad, String categoriaFiscal, String dni, int id,
                  double precio, String vendedor, String producto,String fecha) {
        this.cantidad = cantidad;
        this.categoriaFiscal = categoriaFiscal;
        this.dni = dni;
        this.id = id;
        this.precio = precio;
        this.vendedor = vendedor;
        this.producto = producto;
        this.fecha = fecha;
    }

    public Ticket() {
    }

    //region GYS
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoriaFiscal() {
        return categoriaFiscal;
    }

    public void setCategoriaFiscal(String categoriaFiscal) {
        this.categoriaFiscal = categoriaFiscal;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
