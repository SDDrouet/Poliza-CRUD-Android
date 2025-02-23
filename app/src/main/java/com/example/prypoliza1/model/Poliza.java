package com.example.prypoliza1.model;

public class Poliza {
    private Long id;
    private String nombre;
    private double valorAuto;
    private int accidentes;
    private String modelo;
    private String edad;
    private double costoPoliza;

    public Poliza() {}

    @Override
    public String toString() {
        return id + ". " + "Póliza de " + nombre +
                ", valorAuto: " + valorAuto +
                ", accidentes: " + accidentes +
                ", modelo: " + modelo +
                ", edad: " + edad +
                ", costoPoliza: " + costoPoliza;
    }

    public Poliza(Long id, String nombre, double valorAuto, int accidentes, String modelo, String edad, double costoPoliza) {
        this.id = id;
        this.nombre = nombre;
        this.valorAuto = valorAuto;
        this.accidentes = accidentes;
        this.modelo = modelo;
        this.edad = edad;
        this.costoPoliza = costoPoliza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValorAuto() {
        return valorAuto;
    }

    public void setValorAuto(double valorAuto) {
        this.valorAuto = valorAuto;
    }

    public int getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(int accidentes) {
        this.accidentes = accidentes;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public double getCostoPoliza() {
        return costoPoliza;
    }

    public void setCostoPoliza(double costoPoliza) {
        this.costoPoliza = costoPoliza;
    }
}
