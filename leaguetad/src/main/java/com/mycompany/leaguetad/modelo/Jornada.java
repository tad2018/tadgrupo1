/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.modelo;

import java.util.ArrayList;

/**
 *
 * @author expositod
 */
public class Jornada {
    private int numero;
    private ArrayList<Partido> resultados;

    public Jornada(int numero, ArrayList<Partido> resultados) {
        this.numero = numero;
        this.resultados = resultados;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Partido> getResultados() {
        return resultados;
    }

    public void setResultados(ArrayList<Partido> resultados) {
        this.resultados = resultados;
    }
    
    
}
