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
public class Estadistica {
    private int goles;
    private int pases;
    private int faltas;
    private int expulsiones;
    private int paradas;
    private int tiros;

    public Estadistica(int goles, int pases, int faltas, int expulsiones, int paradas, int tiros) {
        this.goles = goles;
        this.pases = pases;
        this.faltas = faltas;
        this.expulsiones = expulsiones;
        this.paradas = paradas;
        this.tiros = tiros;
    }
    
    
    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getPases() {
        return pases;
    }

    public void setPases(int pases) {
        this.pases = pases;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getExpulsiones() {
        return expulsiones;
    }

    public void setExpulsiones(int expulsiones) {
        this.expulsiones = expulsiones;
    }

    public int getParadas() {
        return paradas;
    }

    public void setParadas(int paradas) {
        this.paradas = paradas;
    }

    public int getTiros() {
        return tiros;
    }

    public void setTiros(int tiros) {
        this.tiros = tiros;
    }
    
    
}
