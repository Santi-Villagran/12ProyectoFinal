/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author santi
 */
public class Alojamiento {
    
    private int idAlojamiento;
    private Ciudad ciudad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipo;
    private double costo;
    private boolean activo;

    public Alojamiento() {
    }

    public Alojamiento(Ciudad ciudad, LocalDate fechaInicio, LocalDate fechaFin, String tipo, double costo, boolean activo) {
        this.ciudad = ciudad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipo = tipo;
        this.costo = costo;
        this.activo = activo;
    }

    public Alojamiento(int idAlojamiento, Ciudad ciudad, LocalDate fechaInicio, LocalDate fechaFin, String tipo, double costo, boolean activo) {
        this.idAlojamiento = idAlojamiento;
        this.ciudad = ciudad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipo = tipo;
        this.costo = costo;
        this.activo = activo;
    }

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setEstado(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Alojamiento: \n" + "ID: " + idAlojamiento + ", Ciudad: " + ciudad + ", Inicio: " + fechaInicio + ", Fin: " + fechaFin + ", Tipo: " + tipo + ", Costo: " + costo + ", Activo: " + activo + '}';
    }
    
    
}
