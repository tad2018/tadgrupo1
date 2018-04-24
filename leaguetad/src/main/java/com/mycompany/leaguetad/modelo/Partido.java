/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.modelo;

/**
 *
 * @author expositod
 */
public class Partido {
    private Equipo local;
    private Equipo visitante;
    private Estadistica estadistica;

    public Partido(Equipo local, Equipo visitante, Estadistica estadistica) {
        this.local = local;
        this.visitante = visitante;
        this.estadistica = estadistica;
    }

    public Equipo getLocal() {
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(Estadistica estadistica) {
        this.estadistica = estadistica;
    }
    
    
}
