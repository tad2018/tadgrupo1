/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.dao;

import com.mycompany.leaguetad.persistence.Tecnico;
import com.mycompany.leaguetad.persistence.Jugador;
import com.mycompany.leaguetad.persistence.PersistenceJDBC;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author expositod
 */
public class EquipoDAO {
    PersistenceJDBC conexion = new PersistenceJDBC();
    Connection conn;
    Statement stmt;
    
    public void createEquipo(String nombre, ArrayList<Jugador> jugadores, ArrayList<Tecnico> equipoTecnico) throws SQLException{

        //conn = conexion.getConnection();
        
        //stmt = conn.createStatement();
    }
   
    
    
}
