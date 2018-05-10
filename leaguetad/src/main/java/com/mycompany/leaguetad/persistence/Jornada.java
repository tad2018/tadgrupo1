package com.mycompany.leaguetad.persistence;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Jornada {
    private int id;
    private Integer numero;
    private Timestamp fecha;
    private Calendario calendarioByCalendarioId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "numero", nullable = true)
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Basic
    @Column(name = "fecha", nullable = true)
    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Jornada jornada = (Jornada) o;
        return id == jornada.id &&
                Objects.equals(numero, jornada.numero) &&
                Objects.equals(fecha, jornada.fecha);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, numero, fecha);
    }

    @ManyToOne
    @JoinColumn(name = "calendario_id", referencedColumnName = "id")
    public Calendario getCalendarioByCalendarioId() {
        return calendarioByCalendarioId;
    }

    public void setCalendarioByCalendarioId(Calendario calendarioByCalendarioId) {
        this.calendarioByCalendarioId = calendarioByCalendarioId;
    }
}
