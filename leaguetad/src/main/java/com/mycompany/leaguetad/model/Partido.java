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
public class Partido implements Serializable {
    private Long id;
    private Equipo local;
    private Equipo visitante;
    private Estadistica estadistica;
    
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column
    public Equipo getLocal() {
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }
    
    @Column
    public Equipo getVisitante() {
        return visitante;
    }
    
    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    @Column
    public Estadistica getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(Estadistica estadistica) {
        this.estadistica = estadistica;
    }


    
}
