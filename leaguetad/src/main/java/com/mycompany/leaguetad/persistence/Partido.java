package com.mycompany.leaguetad.persistence;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Partido {
    private int id;
    private Equipo equipoByLocalId;
    private Equipo equipoByVisitanteId;
    private Jornada jornadaByJornadaId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Partido partido = (Partido) o;
        return id == partido.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "local_id", referencedColumnName = "id")
    public Equipo getEquipoByLocalId() {
        return equipoByLocalId;
    }

    public void setEquipoByLocalId(Equipo equipoByLocalId) {
        this.equipoByLocalId = equipoByLocalId;
    }

    @ManyToOne
    @JoinColumn(name = "visitante_id", referencedColumnName = "id")
    public Equipo getEquipoByVisitanteId() {
        return equipoByVisitanteId;
    }

    public void setEquipoByVisitanteId(Equipo equipoByVisitanteId) {
        this.equipoByVisitanteId = equipoByVisitanteId;
    }

    @ManyToOne
    @JoinColumn(name = "jornada_id", referencedColumnName = "id")
    public Jornada getJornadaByJornadaId() {
        return jornadaByJornadaId;
    }

    public void setJornadaByJornadaId(Jornada jornadaByJornadaId) {
        this.jornadaByJornadaId = jornadaByJornadaId;
    }
}
