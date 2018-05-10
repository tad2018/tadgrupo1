package com.mycompany.leaguetad.persistence;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Liga {
    private int id;
    private String nombre;
    private String pais;

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
    @Column(name = "pais", nullable = true, length = 45)
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Liga liga = (Liga) o;
        return id == liga.id &&
                Objects.equals(nombre, liga.nombre) &&
                Objects.equals(pais, liga.pais);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nombre, pais);
    }
}
