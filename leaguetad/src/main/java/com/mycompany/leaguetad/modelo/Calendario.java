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
public class Calendario {
    private String anyo;
    private ArrayList<Jornada> jornadas;

    public Calendario(String anyo, ArrayList<Jornada> jornadas) {
        this.anyo = anyo;
        this.jornadas = jornadas;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public ArrayList<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(ArrayList<Jornada> jornadas) {
        this.jornadas = jornadas;
    }
    
}
