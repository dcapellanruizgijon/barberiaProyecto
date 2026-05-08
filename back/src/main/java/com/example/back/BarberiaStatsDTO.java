package com.example.back;

public class BarberiaStatsDTO {

    private long citasHoy;
    private double ingresosHoy;
    private long barberosActivos;
    private String proximaCita;

    public long getCitasHoy() {
        return citasHoy;
    }

    public void setCitasHoy(long citasHoy) {
        this.citasHoy = citasHoy;
    }

    public double getIngresosHoy() {
        return ingresosHoy;
    }

    public void setIngresosHoy(double ingresosHoy) {
        this.ingresosHoy = ingresosHoy;
    }

    public long getBarberosActivos() {
        return barberosActivos;
    }

    public void setBarberosActivos(long barberosActivos) {
        this.barberosActivos = barberosActivos;
    }

    public String getProximaCita() {
        return proximaCita;
    }

    public void setProximaCita(String proximaCita) {
        this.proximaCita = proximaCita;
    }

    // Constructores, Getters y Setters
}
