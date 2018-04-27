/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.model;
import java.io.Serializable;
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
public class Estadistica implements Serializable {
    private Long id;
    private int goles;
    private int pases;
    private int faltas;
    private int expulsiones;
    private int paradas;
    private int tiros;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    @Column
    public int getPases() {
        return pases;
    }

    public void setPases(int pases) {
        this.pases = pases;
    }

    @Column
    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    @Column
    public int getExpulsiones() {
        return expulsiones;
    }

    public void setExpulsiones(int expulsiones) {
        this.expulsiones = expulsiones;
    }

    @Column
    public int getParadas() {
        return paradas;
    }

    public void setParadas(int paradas) {
        this.paradas = paradas;
    }

    @Column
    public int getTiros() {
        return tiros;
    }

    public void setTiros(int tiros) {
        this.tiros = tiros;
    }
    
}
