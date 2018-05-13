/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Calendario;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author expositod
 */
public class CalendarioDAO {
    Session session = null;

    public CalendarioDAO() {
        this.session = PersistenceJDBC.getSession();
    }
    
    public Calendario getCalendario(int liga, int anyo){
        Calendario calendario = null;
        
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Calendario where liga_id = '" + liga + "' and anyo like '" + anyo + "%'");
        tx.commit();
        calendario = (Calendario) q.uniqueResult();
        
        return calendario;
    } 
}
