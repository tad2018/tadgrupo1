package com.mycompany.leaguetad.persistence;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estadistica {
    private int id;
    private Integer golesLocal;
    private Integer golesVisitante;
    private Integer pasesLocal;
    private Integer pasesVisitante;
    private Integer faltasLocal;
    private Integer faltasVisitante;
    private Integer tirosLocal;
    private Integer tirosVisitante;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "golesLocal", nullable = true)
    public Integer getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(Integer golesLocal) {
        this.golesLocal = golesLocal;
    }

    @Basic
    @Column(name = "golesVisitante", nullable = true)
    public Integer getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(Integer golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    @Basic
    @Column(name = "pasesLocal", nullable = true)
    public Integer getPasesLocal() {
        return pasesLocal;
    }

    public void setPasesLocal(Integer pasesLocal) {
        this.pasesLocal = pasesLocal;
    }

    @Basic
    @Column(name = "pasesVisitante", nullable = true)
    public Integer getPasesVisitante() {
        return pasesVisitante;
    }

    public void setPasesVisitante(Integer pasesVisitante) {
        this.pasesVisitante = pasesVisitante;
    }

    @Basic
    @Column(name = "faltasLocal", nullable = true)
    public Integer getFaltasLocal() {
        return faltasLocal;
    }

    public void setFaltasLocal(Integer faltasLocal) {
        this.faltasLocal = faltasLocal;
    }

    @Basic
    @Column(name = "faltasVisitante", nullable = true)
    public Integer getFaltasVisitante() {
        return faltasVisitante;
    }

    public void setFaltasVisitante(Integer faltasVisitante) {
        this.faltasVisitante = faltasVisitante;
    }

    @Basic
    @Column(name = "tirosLocal", nullable = true)
    public Integer getTirosLocal() {
        return tirosLocal;
    }

    public void setTirosLocal(Integer tirosLocal) {
        this.tirosLocal = tirosLocal;
    }

    @Basic
    @Column(name = "tirosVisitante", nullable = true)
    public Integer getTirosVisitante() {
        return tirosVisitante;
    }

    public void setTirosVisitante(Integer tirosVisitante) {
        this.tirosVisitante = tirosVisitante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Estadistica that = (Estadistica) o;
        return id == that.id &&
                Objects.equals(golesLocal, that.golesLocal) &&
                Objects.equals(golesVisitante, that.golesVisitante) &&
                Objects.equals(pasesLocal, that.pasesLocal) &&
                Objects.equals(pasesVisitante, that.pasesVisitante) &&
                Objects.equals(faltasLocal, that.faltasLocal) &&
                Objects.equals(faltasVisitante, that.faltasVisitante) &&
                Objects.equals(tirosLocal, that.tirosLocal) &&
                Objects.equals(tirosVisitante, that.tirosVisitante);
    }

    @Override
    public int hashCode() {

        return Objects
                .hash(id, golesLocal, golesVisitante, pasesLocal, pasesVisitante, faltasLocal, faltasVisitante, tirosLocal, tirosVisitante);
    }
}
