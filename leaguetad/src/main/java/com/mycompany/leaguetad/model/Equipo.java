/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author expositod
 */
@Table
@Entity
public class Equipo implements Serializable {
    private Long id;
    private String nombre;
    private Liga liga;
    private ArrayList<Jugador> jugadores;
    private ArrayList<EquipoTecnico> equipoTecnico;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column
    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    @Column
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Column
    public ArrayList<EquipoTecnico> getEquipoTecnico() {
        return equipoTecnico;
    }

    public void setEquipoTecnico(ArrayList<EquipoTecnico> equipoTecnico) {
        this.equipoTecnico = equipoTecnico;
    }

    
}
