/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Calendario;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author expositod
 */
public class CalendarioDAO {
    Session session = null;
    public CalendarioDAO(){
        this.session = PersistenceJDBC.getSession();
    }

    public List<Calendario> getAnyos(){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Calendario");
        List<Calendario> calendarios = (List<Calendario>) q.list();
        tx.commit();
        Iterator it = calendarios.iterator();
        while(it.hasNext()){
            Calendario c = (Calendario)it.next();
            long timestamp = c.getAnyo().getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);
            System.out.println("Año: "+cal.get(Calendar.YEAR));
        }
        return calendarios;
    }
    
    public Calendario getCalendario(Integer anyo, Integer idLiga){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Calendario where liga_id = "+idLiga);
        List<Calendario> calendarios = (List<Calendario>) q.list();
        tx.commit();
        Iterator it = calendarios.iterator();
        while (it.hasNext()){
            Calendario c = (Calendario)it.next();
            long timestamp = c.getAnyo().getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);
            if (cal.get(Calendar.YEAR) == anyo){
                return c;
            }
        }
        return null;
    }
    
}
