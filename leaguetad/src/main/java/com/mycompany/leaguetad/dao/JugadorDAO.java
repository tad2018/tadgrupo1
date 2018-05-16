/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Jugador;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

/**
 *
 * @author expositod
 */
public class JugadorDAO {
    Session session = null;
    public JugadorDAO(){
        this.session = PersistenceJDBC.getSession();
    }
    
    public List<Jugador> getJugadoresIdEquipo(Integer id){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Jugador where equipo_id = "+ id);
        List<Jugador> jugadores = (List<Jugador>) q.list();
        tx.commit();
        this.session.close();
        return jugadores;
    }
}
