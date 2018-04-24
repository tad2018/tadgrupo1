/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad.modelo;

/**
 *
 * @author expositod
 */
public class EquipoTecnico {
    private String nombre;
    private String puesto;

    public EquipoTecnico(String nombre, String puesto) {
        this.nombre = nombre;
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
}
