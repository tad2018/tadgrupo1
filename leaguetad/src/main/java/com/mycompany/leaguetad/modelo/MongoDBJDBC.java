/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.modelo;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import java.net.UnknownHostException;

/**
 *
 * @author expositod
 */
public class MongoDBJDBC {
    public DB connect() throws UnknownHostException{
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // Now connect to your databases
        DB db = mongoClient.getDB("TAD_EPD08");
        System.out.println("Connect to database successfully");
        return db;
    }
}
