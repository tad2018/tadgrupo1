/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import com.mycompany.leaguetad.persistence.Tecnico;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author expositod
 */
public class EquipoTecnicoDAO {
    Session session = null;
    public EquipoTecnicoDAO(){
        this.session = PersistenceJDBC.getSession();
    }
    
    public List<Tecnico> getEquipoTecnicoIdEquipo(Integer id){
        this.session = PersistenceJDBC.getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Tecnico where equipo_id = "+ id);
        List<Tecnico> tecnicos = (List<Tecnico>) q.list();
        tx.commit();
        this.session.close();
        return tecnicos;
    }
}
