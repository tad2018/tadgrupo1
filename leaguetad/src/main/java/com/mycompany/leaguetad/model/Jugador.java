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
 * @author abarroso
 */
@Entity
@Table(name = "jugador", catalog = "leaguetad", schema = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j")
        , @NamedQuery(name = "Jugador.findById", query = "SELECT j FROM Jugador j WHERE j.id = :id")
        , @NamedQuery(name = "Jugador.findByNombre", query = "SELECT j FROM Jugador j WHERE j.nombre = :nombre")
        , @NamedQuery(name = "Jugador.findByNacionalidad",
        query = "SELECT j FROM Jugador j WHERE j.nacionalidad = :nacionalidad")
        , @NamedQuery(name = "Jugador.findByPosicion", query = "SELECT j FROM Jugador j WHERE j.posicion = :posicion")
        , @NamedQuery(name = "Jugador.findByEdad", query = "SELECT j FROM Jugador j WHERE j.edad = :edad")
        , @NamedQuery(name = "Jugador.findByGoles", query = "SELECT j FROM Jugador j WHERE j.goles = :goles")
        , @NamedQuery(name = "Jugador.findByPases", query = "SELECT j FROM Jugador j WHERE j.pases = :pases")
        , @NamedQuery(name = "Jugador.findByFaltas", query = "SELECT j FROM Jugador j WHERE j.faltas = :faltas")
        , @NamedQuery(name = "Jugador.findByExpulsiones",
        query = "SELECT j FROM Jugador j WHERE j.expulsiones = :expulsiones")
        , @NamedQuery(name = "Jugador.findByParadas", query = "SELECT j FROM Jugador j WHERE j.paradas = :paradas")
        , @NamedQuery(name = "Jugador.findByTiros", query = "SELECT j FROM Jugador j WHERE j.tiros = :tiros") })
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "posicion")
    private String posicion;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "goles")
    private Integer goles;
    @Column(name = "pases")
    private Integer pases;
    @Column(name = "faltas")
    private Integer faltas;
    @Column(name = "expulsiones")
    private Integer expulsiones;
    @Column(name = "paradas")
    private Integer paradas;
    @Column(name = "tiros")
    private Integer tiros;
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    @ManyToOne
    private Equipo equipoId;

    public Jugador() {
    }

    public Jugador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getGoles() {
        return goles;
    }

    public void setGoles(Integer goles) {
        this.goles = goles;
    }

    public Integer getPases() {
        return pases;
    }

    public void setPases(Integer pases) {
        this.pases = pases;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    public Integer getExpulsiones() {
        return expulsiones;
    }

    public void setExpulsiones(Integer expulsiones) {
        this.expulsiones = expulsiones;
    }

    public Integer getParadas() {
        return paradas;
    }

    public void setParadas(Integer paradas) {
        this.paradas = paradas;
    }

    public Integer getTiros() {
        return tiros;
    }

    public void setTiros(Integer tiros) {
        this.tiros = tiros;
    }

    public Equipo getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Equipo equipoId) {
        this.equipoId = equipoId;
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
        if (!(object instanceof Jugador)) {
            return false;
        }
        Jugador other = (Jugador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.leaguetad.model.Jugador[ id=" + id + " ]";
    }

}
