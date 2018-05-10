package com.mycompany.leaguetad.persistence;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tecnico {
    private int id;
    private String nombre;
    private String puesto;
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
    @Column(name = "puesto", nullable = true, length = 100)
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tecnico tecnico = (Tecnico) o;
        return id == tecnico.id &&
                Objects.equals(nombre, tecnico.nombre) &&
                Objects.equals(puesto, tecnico.puesto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nombre, puesto);
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
