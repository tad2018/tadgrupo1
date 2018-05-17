/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Calendario;
import com.mycompany.leaguetad.persistence.Jornada;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author expositod
 */
public class JornadaDAO {
    Session session = null;

    public JornadaDAO() {
        this.session = PersistenceJDBC.getSession();
    }

    public List<Jornada> getJornadas(Integer idCalendario) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Jornada where calendario_id = " + idCalendario);
        List<Jornada> jornadas = (List<Jornada>) q.list();
        tx.commit();
        this.session.close();
        return jornadas;
    }

    public Jornada getJornada(int numJornada, Calendario calendario) {
        Jornada jornada = null;

        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(
                "from Jornada where numero = '" + numJornada + "' and calendario_id ='" + calendario.getId() + "'");
        jornada = (Jornada) q.uniqueResult();
        tx.commit();
        this.session.close();
        return jornada;
    }

    public Jornada obtenerJornadaPorFecha(String fecha) {
        Jornada jornada = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Jornada where fecha = '" + fecha + "'");
        jornada = (Jornada) q.uniqueResult();
        tx.commit();
        this.session.close();
        return jornada;
    }

    public void crearJornada(Jornada jornada) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.save(jornada);
        tx.commit();
        this.session.close();
    }

    public void actualizarJornada(Jornada jornada) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.update(jornada);
        tx.commit();
        this.session.close();
    }

    public void borrarJornada(Jornada jornada) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.delete(jornada);
        tx.commit();
        this.session.close();
    }
}
