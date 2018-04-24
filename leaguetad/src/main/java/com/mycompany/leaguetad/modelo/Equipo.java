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
public class Equipo {
    private String nombre;
    private ArrayList<Jugador> jugadores;
    private ArrayList<EquipoTecnico> equipoTecnico;

    public Equipo(String nombre, ArrayList<Jugador> jugadores, ArrayList<EquipoTecnico> equipoTecnico) {
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.equipoTecnico = equipoTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<EquipoTecnico> getEquipoTecnico() {
        return equipoTecnico;
    }

    public void setEquipoTecnico(ArrayList<EquipoTecnico> equipoTecnico) {
        this.equipoTecnico = equipoTecnico;
    }
    
    
}
