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
public class Calendario {
    private int id;
    private Timestamp anyo;
    private Liga ligaId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "anyo", nullable = true)
    public Timestamp getAnyo() {
        return anyo;
    }

    public void setAnyo(Timestamp anyo) {
        this.anyo = anyo;
    }
    
    @ManyToOne
    @JoinColumn(name = "liga_id", referencedColumnName = "id")
    public Liga getLigaId() {
        return ligaId;
    }

    public void setLigaId(Liga ligaId) {
        this.ligaId = ligaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Calendario that = (Calendario) o;
        return id == that.id &&
                Objects.equals(anyo, that.anyo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, anyo);
    }
}
