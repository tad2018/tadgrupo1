/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Estadistica;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author expositod
 */
public class EstadisticaDAO {
    Session session = null;

    public EstadisticaDAO() {
        this.session = PersistenceJDBC.getSession();
    }

    public List<Estadistica> getEstadisticas() {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Estadistica");
        List<Estadistica> estadisticas = (List<Estadistica>) q.list();
        tx.commit();
        this.session.close();
        return estadisticas;
    }

    public Estadistica getEstadistica(Integer idPartido) {
        Estadistica estadistica = null;

        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Estadistica where partido_id = " + idPartido);
        tx.commit();
        this.session.close();
        estadistica = (Estadistica) q.uniqueResult();

        return estadistica;
    }

    public void crearEstadistica(Estadistica estadistica) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.save(estadistica);
        tx.commit();
        this.session.close();
    }

    public void actualizarEstadistica(Estadistica estadistica) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.update(estadistica);
        tx.commit();
        this.session.close();
    }

    public void borrarEstadistica(Estadistica estadistica) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.delete(estadistica);
        tx.commit();
        this.session.close();
    }
}
