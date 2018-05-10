package com.mycompany.leaguetad.persistence;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Equipo {
    private int id;
    private String nombre;
    private Integer puntos;

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
    @Column(name = "puntos", nullable = true)
    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Equipo equipo = (Equipo) o;
        return id == equipo.id &&
                Objects.equals(nombre, equipo.nombre) &&
                Objects.equals(puntos, equipo.puntos);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nombre, puntos);
    }
}
