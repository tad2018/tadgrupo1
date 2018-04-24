/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.modelo;

import java.util.ArrayList;

/**
 *
 * @author expositod
 */
public class Liga {
    private ArrayList<Calendario> calendarios;
    private String nombre;
    private String pais;
    private ArrayList<Equipo> equipos;

    public Liga(ArrayList<Calendario> calendarios, String nombre, String pais, ArrayList<Equipo> equipos) {
        this.calendarios = calendarios;
        this.nombre = nombre;
        this.pais = pais;
        this.equipos = equipos;
    }

    public ArrayList<Calendario> getCalendarios() {
        return calendarios;
    }

    public void setCalendarios(ArrayList<Calendario> calendarios) {
        this.calendarios = calendarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }
    
    
}
