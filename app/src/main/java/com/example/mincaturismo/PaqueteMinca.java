package com.example.mincaturismo;

public class PaqueteMinca {
    String nombreTuristico, descripcion, fotoSitio;

    public PaqueteMinca(String nombreTuristico, String descripcion, String fotoSitio) {
        this.nombreTuristico = nombreTuristico;
        this.descripcion = descripcion;
        this.fotoSitio = fotoSitio;
    }

    public String getNombreTuristico() {
        return nombreTuristico;
    }

    public void setNombreTuristico(String nombreTuristico) {
        this.nombreTuristico = nombreTuristico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotoSitio() {
        return fotoSitio;
    }

    public void setFotoSitio(String fotoSitio) {
        this.fotoSitio = fotoSitio;
    }
}