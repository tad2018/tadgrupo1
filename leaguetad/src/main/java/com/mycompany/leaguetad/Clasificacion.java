/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad;

import com.mycompany.leaguetad.dao.LigaDAO;
import com.mycompany.leaguetad.persistence.Equipo;
import com.mycompany.leaguetad.persistence.Liga;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author expositod
 */
@Theme("tests-valo-dark")
public class Clasificacion extends UI{

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setStyleName("fondo");
        Button dashboard = new Button("Volver al Dashboard");
        
        dashboard.addClickListener( e -> {
            getPage().setLocation("/dashboard");
        });
        TabSheet sample = new TabSheet();
        
        final VerticalLayout layoutSantander = new VerticalLayout();
        layoutSantander.setSizeFull();
        layoutSantander.setMargin(true);
        sample.addTab(layoutSantander,"LIGA SANTANDER");
        
        final Table tablaLigaEspañola = new Table();
        tablaLigaEspañola.setWidth(100, UNITS_PERCENTAGE);
        tablaLigaEspañola.setSelectable(true);
        tablaLigaEspañola.setMultiSelect(true);
        tablaLigaEspañola.setImmediate(true);
        tablaLigaEspañola.addContainerProperty("POSICIÓN", Integer.class, null);
        tablaLigaEspañola.addContainerProperty("EQUIPOS", String.class, null);
        tablaLigaEspañola.addContainerProperty("PUNTOS", Integer.class, null);
        
        LigaDAO ligadao = new LigaDAO();
        final Equipo[] listadoEquipos = ligadao.getClasificacionLigaEspanola();
        for(int i=0; i < listadoEquipos.length; i++){
            Equipo e = (Equipo) listadoEquipos[i];
            tablaLigaEspañola.addItem(new Object[]{i+1, e.getNombre(), e.getPuntos()}, i+1);
        }
        tablaLigaEspañola.setPageLength(listadoEquipos.length);
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        // Image as a file resource
        FileResource resourceSantander = new FileResource(new File(basepath +"/VAADIN/themes/tests-valo-dark/img/liga_santander_logo.png"));

        // Show the image in the application
        Image logoSantander = new Image("", resourceSantander);
        logoSantander.setWidth("200px");
        logoSantander.setHeight("190px");
        layoutSantander.addComponents(logoSantander, tablaLigaEspañola);
        layoutSantander.setComponentAlignment(logoSantander, Alignment.TOP_CENTER);
        
        final VerticalLayout layoutPremier = new VerticalLayout();
        layoutPremier.setMargin(true);
        sample.addTab(layoutPremier, "Premier League");
        final Label titleLigaInglesa = new Label("PREMIER LEAGUE");
        final Table tablaLigaInglesa = new Table();
        tablaLigaInglesa.setWidth(100, UNITS_PERCENTAGE);
        tablaLigaInglesa.setSelectable(true);
        tablaLigaInglesa.setMultiSelect(true);
        tablaLigaInglesa.setImmediate(true);
        tablaLigaInglesa.addContainerProperty("POSICIÓN", Integer.class, null);
        tablaLigaInglesa.addContainerProperty("EQUIPOS", String.class, null);
        tablaLigaInglesa.addContainerProperty("PUNTOS", Integer.class, null);
        
        final Equipo[] listadoEquiposInglesa = ligadao.getClasificacionLigaInglesa();
        for(int i=0; i < listadoEquiposInglesa.length; i++){
            Equipo e = (Equipo) listadoEquiposInglesa[i];
            tablaLigaInglesa.addItem(new Object[]{i+1, e.getNombre(), e.getPuntos()}, i+1);
        }
        tablaLigaInglesa.setPageLength(listadoEquipos.length);
        FileResource resourcePremier = new FileResource(new File(basepath +"/VAADIN/themes/tests-valo-dark/img/premier_league_logo.png"));

        // Show the image in the application
        Image logoPremier = new Image("", resourcePremier);
        logoPremier.setWidth("200px");
        logoPremier.setHeight("190px");
        layoutPremier.addComponents(logoPremier, tablaLigaInglesa);
        layoutPremier.setComponentAlignment(logoPremier, Alignment.TOP_CENTER);
        
        final VerticalLayout layoutCalcio = new VerticalLayout();
        layoutCalcio.setMargin(true);
        sample.addTab(layoutCalcio, "Lega Calcio");
        final Label titleLigaItaliana = new Label("LEGA CALCIO");
        final Table tablaLigaItaliana = new Table();
        tablaLigaItaliana.setWidth(100, UNITS_PERCENTAGE);
        tablaLigaItaliana.setSelectable(true);
        tablaLigaItaliana.setMultiSelect(true);
        tablaLigaItaliana.setImmediate(true);
        tablaLigaItaliana.addContainerProperty("POSICIÓN", Integer.class, null);
        tablaLigaItaliana.addContainerProperty("EQUIPOS", String.class, null);
        tablaLigaItaliana.addContainerProperty("PUNTOS", Integer.class, null);
        
        final Equipo[] listadoEquiposItaliana = ligadao.getClasificacionLigaItaliana();
        for(int i=0; i < listadoEquiposItaliana.length; i++){
            Equipo e = (Equipo) listadoEquiposItaliana[i];
            tablaLigaItaliana.addItem(new Object[]{i+1, e.getNombre(), e.getPuntos()}, i+1);
        }
        tablaLigaItaliana.setPageLength(listadoEquipos.length);
        FileResource resourceCalcio = new FileResource(new File(basepath +"/VAADIN/themes/tests-valo-dark/img/calcio_logo.png"));

        // Show the image in the application
        Image logoCalcio = new Image("", resourceCalcio);
        logoCalcio.setWidth("200px");
        logoCalcio.setHeight("190px");
        layoutCalcio.addComponents(logoCalcio, tablaLigaItaliana);
        layoutCalcio.setComponentAlignment(logoCalcio, Alignment.TOP_CENTER);
        
        layout.addComponents(sample,dashboard);
        layout.setComponentAlignment(dashboard, Alignment.BOTTOM_RIGHT);
        setContent(layout);
    }
    
    @WebServlet(urlPatterns = "/clasificacion/*", name = "ClasificacionServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Clasificacion.class, productionMode = false)
    public static class ClasificacionServlet extends VaadinServlet {
    }
}
