/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abarroso
 */
@Entity
@Table(name = "estadistica", catalog = "leaguetad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadistica.findAll", query = "SELECT e FROM Estadistica e")
    , @NamedQuery(name = "Estadistica.findById", query = "SELECT e FROM Estadistica e WHERE e.id = :id")
    , @NamedQuery(name = "Estadistica.findByGolesLocal", query = "SELECT e FROM Estadistica e WHERE e.golesLocal = :golesLocal")
    , @NamedQuery(name = "Estadistica.findByGolesVisitante", query = "SELECT e FROM Estadistica e WHERE e.golesVisitante = :golesVisitante")
    , @NamedQuery(name = "Estadistica.findByPasesLocal", query = "SELECT e FROM Estadistica e WHERE e.pasesLocal = :pasesLocal")
    , @NamedQuery(name = "Estadistica.findByPasesVisitante", query = "SELECT e FROM Estadistica e WHERE e.pasesVisitante = :pasesVisitante")
    , @NamedQuery(name = "Estadistica.findByFaltasLocal", query = "SELECT e FROM Estadistica e WHERE e.faltasLocal = :faltasLocal")
    , @NamedQuery(name = "Estadistica.findByFaltasVisitante", query = "SELECT e FROM Estadistica e WHERE e.faltasVisitante = :faltasVisitante")
    , @NamedQuery(name = "Estadistica.findByTirosLocal", query = "SELECT e FROM Estadistica e WHERE e.tirosLocal = :tirosLocal")
    , @NamedQuery(name = "Estadistica.findByTirosVisitante", query = "SELECT e FROM Estadistica e WHERE e.tirosVisitante = :tirosVisitante")})
public class Estadistica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "golesLocal")
    private Integer golesLocal;
    @Column(name = "golesVisitante")
    private Integer golesVisitante;
    @Column(name = "pasesLocal")
    private Integer pasesLocal;
    @Column(name = "pasesVisitante")
    private Integer pasesVisitante;
    @Column(name = "faltasLocal")
    private Integer faltasLocal;
    @Column(name = "faltasVisitante")
    private Integer faltasVisitante;
    @Column(name = "tirosLocal")
    private Integer tirosLocal;
    @Column(name = "tirosVisitante")
    private Integer tirosVisitante;
    @JoinColumn(name = "partido_id", referencedColumnName = "id")
    @ManyToOne
    private Partido partidoId;

    public Estadistica() {
    }

    public Estadistica(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(Integer golesLocal) {
        this.golesLocal = golesLocal;
    }

    public Integer getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(Integer golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public Integer getPasesLocal() {
        return pasesLocal;
    }

    public void setPasesLocal(Integer pasesLocal) {
        this.pasesLocal = pasesLocal;
    }

    public Integer getPasesVisitante() {
        return pasesVisitante;
    }

    public void setPasesVisitante(Integer pasesVisitante) {
        this.pasesVisitante = pasesVisitante;
    }

    public Integer getFaltasLocal() {
        return faltasLocal;
    }

    public void setFaltasLocal(Integer faltasLocal) {
        this.faltasLocal = faltasLocal;
    }

    public Integer getFaltasVisitante() {
        return faltasVisitante;
    }

    public void setFaltasVisitante(Integer faltasVisitante) {
        this.faltasVisitante = faltasVisitante;
    }

    public Integer getTirosLocal() {
        return tirosLocal;
    }

    public void setTirosLocal(Integer tirosLocal) {
        this.tirosLocal = tirosLocal;
    }

    public Integer getTirosVisitante() {
        return tirosVisitante;
    }

    public void setTirosVisitante(Integer tirosVisitante) {
        this.tirosVisitante = tirosVisitante;
    }

    public Partido getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(Partido partidoId) {
        this.partidoId = partidoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadistica)) {
            return false;
        }
        Estadistica other = (Estadistica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.leaguetad.model.Estadistica[ id=" + id + " ]";
    }
    
}
