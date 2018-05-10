package com.mycompany.leaguetad.persistence;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Jugador {
    private int id;
    private String nombre;
    private String nacionalidad;
    private String posicion;
    private Integer edad;
    private Integer goles;
    private Integer pases;
    private Integer faltas;
    private Integer expulsiones;
    private Integer paradas;
    private Integer tiros;
    private Equipo equipoByEquipoId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "nacionalidad", nullable = true, length = 100)
    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Basic
    @Column(name = "posicion", nullable = true, length = 45)
    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Basic
    @Column(name = "edad", nullable = true)
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Basic
    @Column(name = "goles", nullable = true)
    public Integer getGoles() {
        return goles;
    }

    public void setGoles(Integer goles) {
        this.goles = goles;
    }

    @Basic
    @Column(name = "pases", nullable = true)
    public Integer getPases() {
        return pases;
    }

    public void setPases(Integer pases) {
        this.pases = pases;
    }

    @Basic
    @Column(name = "faltas", nullable = true)
    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    @Basic
    @Column(name = "expulsiones", nullable = true)
    public Integer getExpulsiones() {
        return expulsiones;
    }

    public void setExpulsiones(Integer expulsiones) {
        this.expulsiones = expulsiones;
    }

    @Basic
    @Column(name = "paradas", nullable = true)
    public Integer getParadas() {
        return paradas;
    }

    public void setParadas(Integer paradas) {
        this.paradas = paradas;
    }

    @Basic
    @Column(name = "tiros", nullable = true)
    public Integer getTiros() {
        return tiros;
    }

    public void setTiros(Integer tiros) {
        this.tiros = tiros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Jugador jugador = (Jugador) o;
        return id == jugador.id &&
                Objects.equals(nombre, jugador.nombre) &&
                Objects.equals(nacionalidad, jugador.nacionalidad) &&
                Objects.equals(posicion, jugador.posicion) &&
                Objects.equals(edad, jugador.edad) &&
                Objects.equals(goles, jugador.goles) &&
                Objects.equals(pases, jugador.pases) &&
                Objects.equals(faltas, jugador.faltas) &&
                Objects.equals(expulsiones, jugador.expulsiones) &&
                Objects.equals(paradas, jugador.paradas) &&
                Objects.equals(tiros, jugador.tiros);
    }

    @Override
    public int hashCode() {

        return Objects
                .hash(id, nombre, nacionalidad, posicion, edad, goles, pases, faltas, expulsiones, paradas, tiros);
    }

    @ManyToOne
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    public Equipo getEquipoByEquipoId() {
        return equipoByEquipoId;
    }

    public void setEquipoByEquipoId(Equipo equipoByEquipoId) {
        this.equipoByEquipoId = equipoByEquipoId;
    }
}
