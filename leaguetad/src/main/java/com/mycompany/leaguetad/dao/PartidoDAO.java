/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Calendario;
import com.mycompany.leaguetad.persistence.Equipo;
import com.mycompany.leaguetad.persistence.Jornada;
import com.mycompany.leaguetad.persistence.Liga;
import com.mycompany.leaguetad.persistence.Partido;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author expositod
 */
public class PartidoDAO {
    Session session = null;

    public PartidoDAO() {
        this.session = PersistenceJDBC.getSession();
    }

    public List<Partido> getPartidos(Integer idJornada) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Partido where jornada_id = " + idJornada);
        List<Partido> partidos = (List<Partido>) q.list();
        tx.commit();
        this.session.close();
        return partidos;
    }
    
    public List<Partido> getTodosLosPartidos(){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Partido");
        List<Partido> partidos = (List<Partido>) q.list();
        tx.commit();
        this.session.close();
        return partidos;
    }
    
    public List<Partido> getPartidosPorLiga(Liga liga){
        List<Partido> lstPartidos = new ArrayList<>();
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Calendario where liga_id = " + liga.getId());
        List<Calendario> calendarios = (List<Calendario>) q.list();
        Iterator it = calendarios.iterator();
        while (it.hasNext()){
            Calendario c = (Calendario) it.next();
            Query q2 = session.createQuery("from Jornada where calendario_id = " + c.getId());
            List<Jornada> jornadas = (List<Jornada>) q2.list();
            Iterator it2 = jornadas.iterator();
            while (it2.hasNext()){
                Jornada j = (Jornada) it2.next();
                Query q3 = session.createQuery("from Partido where jornada_id = " + j.getId());
                lstPartidos.addAll((List<Partido>) q3.list());
            }
        }
        tx.commit();
        this.session.close();
        return lstPartidos;
    }
    
    public List<Partido> getPartidosNullPorLiga(Liga liga) {
        List<Partido> lstPartidos = new ArrayList<>();
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Equipo where liga_id = " + liga.getId());
        List<Equipo> lstEquipo = (List<Equipo>) q.list();
        Iterator it = lstEquipo.iterator();
        while (it.hasNext()){
            Equipo e = (Equipo) it.next();
            Query q2 = session.createQuery("from Partido where local_id = " + e.getId() + " and jornada_id is null");
            lstPartidos.addAll((List<Partido>) q2.list());
        }
        return lstPartidos;
    }

    public List<Partido> getPartidosPorJornada(Jornada jornada) {
        List<Partido> lstPartidos = new ArrayList<>();

        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Partido where jornada_id = '" + jornada.getId() + "'");
        lstPartidos = (List<Partido>) q.getResultList();
        tx.commit();
        this.session.close();
        return lstPartidos;
    }

    public List<Partido> getPartidosSinJornada() {
        List<Partido> lstPartidos = new ArrayList<>();

        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Partido where jornada_id is null");
        lstPartidos = (List<Partido>) q.getResultList();
        tx.commit();
        this.session.close();
        return lstPartidos;
    }

    public void updateJornadaAPartido(Jornada jornada, int idPartido) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session
                .createQuery("update Partido set jornada_id = " + jornada.getId() + " where id = " + idPartido);
        q.executeUpdate();
        tx.commit();
        this.session.close();
    }

    public List<Partido> getPartidos() {
        List<Partido> lstPartidos = new ArrayList<>();
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Partido where jornada_id is not null");
        lstPartidos = (List<Partido>) q.getResultList();
        tx.commit();
        this.session.close();
        return lstPartidos;
    }

    public Date obtenerFechaPartido(int id) {
        Date fecha = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Partido where id = " + id);
        Partido p = (Partido) q.uniqueResult();
        Jornada j = p.getJornadaByJornadaId();
        fecha = j.getFecha();
        tx.commit();
        this.session.close();
        return fecha;
    }

    public Integer obtenerJornadaPartido(int id) {
        Integer numJornada = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Partido where id = " + id);
        Partido p = (Partido) q.uniqueResult();
        Jornada j = p.getJornadaByJornadaId();
        numJornada = j.getNumero();
        tx.commit();
        this.session.close();
        return numJornada;
    }

    public String obtenerLigaPartido(Equipo equipo) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Equipo where id = " + equipo.getId());
        Equipo e = (Equipo) q.uniqueResult();
        Liga l = e.getLigaByLigaId();
        tx.commit();
        this.session.close();
        return l.getNombre();
    }

    public void crearPartido(Partido partido) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.save(partido);
        tx.commit();
        this.session.close(); 
    }

    public void actualizarPartido(Partido partido) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.update(partido);
        tx.commit();
        this.session.close();
    }

    public void borrarPartido(Partido partido) {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        this.session.delete(partido);
        tx.commit();
        this.session.close();
    }

    
    
    
}
