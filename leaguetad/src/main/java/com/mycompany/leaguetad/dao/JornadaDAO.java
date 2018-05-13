/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Calendario;
import com.mycompany.leaguetad.persistence.Jornada;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author expositod
 */
public class JornadaDAO {
    Session session = null;

    public JornadaDAO() {
        this.session = PersistenceJDBC.getSession();
    }
    
    public Jornada getJornada(int numJornada, Calendario calendario){
        Jornada jornada = null;
        
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Jornada where numero = '" + numJornada + "' and calendario_id ='" + calendario.getId() + "'");
        jornada = (Jornada) q.uniqueResult();
        tx.commit();
        return jornada;
    }
}
