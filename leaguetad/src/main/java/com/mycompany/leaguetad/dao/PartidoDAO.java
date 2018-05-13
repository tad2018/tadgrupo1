/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Partido;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author expositod
 */
public class PartidoDAO {
    Session session = null;
    public PartidoDAO(){
        this.session = PersistenceJDBC.getSession();
    }
    
    public List<Partido> getPartidos(Integer idJornada){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Partido where jornada_id = "+idJornada);
        List<Partido> partidos = (List<Partido>) q.list();
        return partidos;
    }
}
