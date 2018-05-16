/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Equipo;
import com.mycompany.leaguetad.persistence.Liga;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author expositod
 */
public class LigaDAO {
    Session session = null;

    public LigaDAO() {
        this.session = PersistenceJDBC.getSession();
    }

    public Liga[] getLigas() {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga");
        List<Liga> ligas = (List<Liga>) q.list();
        tx.commit();
        this.session.close();
        Liga[] listaLigas = new Liga[ligas.size()];
        Iterator it = ligas.iterator();
        int cont = 0;
        while (it.hasNext()) {
            Liga l = (Liga) it.next();
            listaLigas[cont++] = l;
        }
        return listaLigas;
    }

    public List<Liga> getLigasLista() {
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga");
        List<Liga> ligas = (List<Liga>) q.list();
        tx.commit();
        this.session.close();
        return ligas;
    }

    public Equipo[] getClasificacionLigaEspanola() {

        Liga liga = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga where pais = 'España'");
        liga = (Liga) q.uniqueResult();
        Query q2 = session.createQuery("from Equipo where liga_id = " + liga.getId() + "");
        List equipos = (List<Equipo>) q2.list();
        tx.commit();
        this.session.close();
        Equipo[] clasificacion = new Equipo[equipos.size()];
        Iterator it = equipos.iterator();
        int cont = 0;
        while (it.hasNext()) {
            Equipo e = (Equipo) it.next();
            clasificacion[cont++] = e;
        }
        //        Arrays.sort(clasificacion);
        return clasificacion;
    }

    public Equipo[] getClasificacionLigaInglesa() {
        Liga liga = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga where pais = 'Inglaterra'");
        liga = (Liga) q.uniqueResult();
        Query q2 = session.createQuery("from Equipo where liga_id = " + liga.getId() + "");
        List equipos = (List<Equipo>) q2.list();
        tx.commit();
        this.session.close();
        Equipo[] clasificacion = new Equipo[equipos.size()];
        Iterator it = equipos.iterator();
        int cont = 0;
        while (it.hasNext()) {
            Equipo e = (Equipo) it.next();
            clasificacion[cont++] = e;
        }
        //        Arrays.sort(clasificacion);
        return clasificacion;
    }

    public List<Equipo> getEquipos(int idLiga) {
        List<Equipo> lstEquipos = new ArrayList<>();
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Equipo where liga_id = " + idLiga);
        lstEquipos = q.getResultList();
        tx.commit();
        this.session.close();
        return lstEquipos;
    }

    public Equipo[] getClasificacionLigaItaliana() {
        Liga liga = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga where pais = 'Italia'");
        liga = (Liga) q.uniqueResult();
        Query q2 = session.createQuery("from Equipo where liga_id = " + liga.getId() + "");
        List equipos = (List<Equipo>) q2.list();
        tx.commit();
        this.session.close();
        Equipo[] clasificacion = new Equipo[equipos.size()];
        Iterator it = equipos.iterator();
        int cont = 0;
        while (it.hasNext()) {
            Equipo e = (Equipo) it.next();
            clasificacion[cont++] = e;
        }
        //        Arrays.sort(clasificacion);
        return clasificacion;
    }

    public List<Equipo> getClasificacionPais(String pais) {
        Liga liga = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga where pais = '" + pais + "'");
        liga = (Liga) q.uniqueResult();
        Query q2 = session.createQuery("from Equipo where liga_id = " + liga.getId() + "");
        List equipos = (List<Equipo>) q2.list();
        tx.commit();
        this.session.close();
        return equipos;
    }

    public Liga buscarLigaporPais(String pais) {
        Liga liga = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga where pais = '" + pais + "'");
        liga = (Liga) q.uniqueResult();
        tx.commit();
        this.session.close();
        if (liga != null) {
            return liga;
        } else {
            return null;
        }
    }

    public Liga buscarLigaporNombre(String nombre) {
        Liga liga = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga where nombre = '" + nombre + "'");
        liga = (Liga) q.uniqueResult();
        tx.commit();
        this.session.close();
        if (liga != null) {
            return liga;
        } else {
            return null;
        }
    }

    public Integer buscarIdLigaPais(String pais) {
        Liga liga = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga where pais = '" + pais + "'");
        liga = (Liga) q.uniqueResult();
        tx.commit();
        this.session.close();
        if (liga != null) {
            return liga.getId();
        } else {
            return null;
        }

    }

    public Integer buscarIdLigaNombre(String nombre) {
        Liga liga = null;
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Liga where nombre = '" + nombre + "'");
        liga = (Liga) q.uniqueResult();
        tx.commit();
        this.session.close();
        if (liga != null) {
            return liga.getId();
        } else {
            return null;
        }
    }
}
