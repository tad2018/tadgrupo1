/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Equipo;
import com.mycompany.leaguetad.persistence.Tecnico;
import com.mycompany.leaguetad.persistence.Jugador;
import com.mycompany.leaguetad.persistence.Liga;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author expositod
 */
public class EquipoDAO {
    
    Session session = null;
    public EquipoDAO(){
        this.session = PersistenceJDBC.getSession();
    }
    
    
    public Hashtable<String, List<Equipo>> getEquiposPorLiga(){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga");
        List ligas = (List<Liga>) q.list();
        Iterator it = ligas.iterator();
        Hashtable<String, List<Equipo>> diccionarioLigas = new Hashtable<String, List<Equipo>>();
        while(it.hasNext()){
            Liga l = (Liga)it.next();
            Query q2 = session.createQuery("from Equipo where liga_id = "+ l.getId());
            List<Equipo> equipos = (List<Equipo>) q2.list();
            diccionarioLigas.put(l.getNombre(),equipos);
        }
        tx.commit();
        return diccionarioLigas;
    }
    
    public List<Equipo> getEquiposIdLiga(Integer id){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Equipo where liga_id = "+ id);
        List<Equipo> equipos = (List<Equipo>) q.list();
        tx.commit();
        return equipos;
    }
    
    public Integer buscarIdEquipoNombre(String nombre){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Equipo where nombre = '"+nombre+"'");
        Equipo equipo = (Equipo) q.uniqueResult();
        tx.commit();
        if(equipo!=null){
            return equipo.getId();
        }
        else{
            return null;
        }
    }
    
    public List<Equipo> getEquiposSinLiga(){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Equipo where liga_id is null");
        List<Equipo> equipos = (List<Equipo>) q.list();
        tx.commit();
        return equipos;
    }
    
    public Equipo getEquipoPorId(int id){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Equipo where id = " + id);
        Equipo equipo = (Equipo) q.uniqueResult();
        tx.commit();
        return equipo;
    }

    public void añadirEquipoALiga(Object itemId, int idLiga) {
        Equipo e = (Equipo) itemId;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("update Equipo set liga_id = " + idLiga + " where nombre = '" + e.getNombre() + "'");
        q.executeUpdate();
        tx.commit();
    }
    
}
