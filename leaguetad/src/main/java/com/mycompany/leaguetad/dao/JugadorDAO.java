/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Jugador;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author expositod
 */
public class JugadorDAO {
    Session session = null;

    public JugadorDAO() {
        this.session = PersistenceJDBC.getSession();
    }

    public List<Jugador> getJugadoresIdEquipo(Integer id) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Jugador where equipo_id = " + id);
        List<Jugador> jugadores = (List<Jugador>) q.list();
        tx.commit();
        this.session.close();
        return jugadores;
    }
    
    public Jugador getJugadoresPorNombre(String nombre) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Jugador where nombre = '" + nombre+"'");
        Jugador jugador = (Jugador) q.uniqueResult();
        tx.commit();
        this.session.close();
        return jugador;
    }

    public void createJugador(Jugador jugador) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.save(jugador);
        tx.commit();
        this.session.close();
    }

    public void actualizarJugador(Jugador jugador) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.update(jugador);
        tx.commit();
        this.session.close();
    }

    public void borrarJugador(Jugador jugador) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.delete(jugador);
        tx.commit();
        this.session.close();
    }
}
