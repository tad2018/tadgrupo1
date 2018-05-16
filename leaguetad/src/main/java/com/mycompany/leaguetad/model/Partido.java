/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author abarroso
 */
@Entity
@Table(name = "partido", catalog = "leaguetad", schema = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Partido.findAll", query = "SELECT p FROM Partido p")
        , @NamedQuery(name = "Partido.findById", query = "SELECT p FROM Partido p WHERE p.id = :id") })
public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "partidoId")
    private List<Estadistica> estadisticaList;
    @JoinColumn(name = "local_id", referencedColumnName = "id")
    @ManyToOne
    private Equipo localId;
    @JoinColumn(name = "jornada_id", referencedColumnName = "id")
    @ManyToOne
    private Jornada jornadaId;
    @JoinColumn(name = "visitante_id", referencedColumnName = "id")
    @ManyToOne
    private Equipo visitanteId;

    public Partido() {
    }

    public Partido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Estadistica> getEstadisticaList() {
        return estadisticaList;
    }

    public void setEstadisticaList(List<Estadistica> estadisticaList) {
        this.estadisticaList = estadisticaList;
    }

    public Equipo getLocalId() {
        return localId;
    }

    public void setLocalId(Equipo localId) {
        this.localId = localId;
    }

    public Jornada getJornadaId() {
        return jornadaId;
    }

    public void setJornadaId(Jornada jornadaId) {
        this.jornadaId = jornadaId;
    }

    public Equipo getVisitanteId() {
        return visitanteId;
    }

    public void setVisitanteId(Equipo visitanteId) {
        this.visitanteId = visitanteId;
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
        if (!(object instanceof Partido)) {
            return false;
        }
        Partido other = (Partido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.leaguetad.model.Partido[ id=" + id + " ]";
    }

}
