/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author expositod
 */
public class PersistenceJDBC {
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB = "leaguetad";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    
    public Connection getConnection() throws SQLException {

        Connection conn;
        
        Properties connectionProps = new Properties();
        connectionProps.put("user", USER);
        connectionProps.put("password", PASSWORD);
        
        conn = DriverManager.getConnection("jdbc:"+DB+"://"+HOST+":"+PORT+"/",connectionProps);
        System.out.println("Connected to database");
        return conn;
    }
}
