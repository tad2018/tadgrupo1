/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad;

import com.mycompany.leaguetad.dao.EquipoDAO;
import com.mycompany.leaguetad.dao.JugadorDAO;
import com.mycompany.leaguetad.dao.LigaDAO;
import com.mycompany.leaguetad.persistence.Equipo;
import com.mycompany.leaguetad.persistence.Jugador;
import com.mycompany.leaguetad.persistence.Liga;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.servlet.annotation.WebServlet;;

/**
 *
 * @author expositod
 */
@Theme("tests-valo-dark")
public class Main extends UI{

    @Override
    protected void init(VaadinRequest request) {
        HorizontalSplitPanel layout = new HorizontalSplitPanel();
        LigaDAO ligadao = new LigaDAO();
        EquipoDAO equipodao = new EquipoDAO();
                
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resourceSantander = new FileResource(new File(basepath +"/VAADIN/themes/tests-valo-dark/img/logo.png"));
        Image logo = new Image("", resourceSantander);
        logo.setWidth("200px");
        logo.setHeight("190px");
        Tree tree = new Tree("LEAGUE TAD");
        VerticalLayout verticalLayout = new VerticalLayout();
        VerticalLayout verticalTree = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setStyleName("fondo");
 
        String clasificacion = new String("Clasificación");
        tree.addItem(clasificacion);
        tree.setChildrenAllowed(clasificacion, true);
        
        final Liga[] ligas = ligadao.getLigas();
        for(int i=0; i < ligas.length; i++){
            Liga l = (Liga) ligas[i];
            String nombre = new String(l.getPais());
            tree.addItem(nombre);
            tree.setParent(nombre, clasificacion);
            tree.setChildrenAllowed(nombre, false);
        }
        
        String equipos = new String("Equipos");
        tree.addItem(equipos);
        
        Hashtable<String, List<Equipo>> equiposLigas = equipodao.getEquiposPorLiga();
        for (Entry<String, List<Equipo>> entry : equiposLigas.entrySet()) {
            String nombreLiga = new String(entry.getKey());
            List<Equipo> equiposLista = entry.getValue();
            tree.addItem(nombreLiga);
            tree.setParent(nombreLiga, equipos);
            tree.setChildrenAllowed(nombreLiga, true);
            Iterator it = equiposLista.iterator();
            while(it.hasNext()){
                Equipo e = (Equipo)it.next();
                tree.addItem(e.getNombre());
                tree.setParent(e.getNombre(), nombreLiga);
                tree.setChildrenAllowed(e.getNombre(), false);
            }
            
        }
        
        tree.addItemClickListener( e -> {
            String itemSelected = e.getItemId().toString();
            if(itemSelected.equals("Clasificación")){
                mostrarClasificacion(verticalLayout);
            }
            else{
                if(ligadao.buscarLigaporPais(itemSelected) != null){
                    mostrarClasificacionPais(verticalLayout, itemSelected);
                }
                else if(ligadao.buscarLigaporNombre(itemSelected) != null){
                    mostrarEquiposPais(verticalLayout, itemSelected);
                }
                else if(equipodao.buscarIdEquipoNombre(itemSelected) != null){
                    mostrarJugadoresEquipo(verticalLayout, itemSelected);
                }
            }
        });
        verticalTree.addComponents(logo,tree);
        verticalTree.setComponentAlignment(logo, Alignment.TOP_CENTER);
        layout.addComponents(verticalTree,verticalLayout);
        layout.setSplitPosition(13);
        layout.setLocked(true);
        setContent(layout);
    }
    
    public static void mostrarClasificacion(VerticalLayout verticalLayout){
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
        sample.addTab(layoutPremier, "PREMIER LEAGUE");
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
        sample.addTab(layoutCalcio, "LEGA CALCIO");
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
        verticalLayout.removeAllComponents();
        verticalLayout.addComponents(sample);
    }
    
    
    public static void mostrarClasificacionPais(VerticalLayout verticalLayout, String pais){
        verticalLayout.setMargin(true);
        final Table tabla = new Table();
        tabla.setWidth(100, UNITS_PERCENTAGE);
        tabla.setSelectable(true);
        tabla.setMultiSelect(true);
        tabla.setImmediate(true);
        tabla.addContainerProperty("POSICIÓN", Integer.class, null);
        tabla.addContainerProperty("EQUIPOS", String.class, null);
        tabla.addContainerProperty("PUNTOS", Integer.class, null);
        
        LigaDAO ligadao = new LigaDAO();
        final List<Equipo> listadoEquipos = ligadao.getClasificacionPais(pais);
        Iterator it = listadoEquipos.iterator();
        int i=1;
        while(it.hasNext()){
            Equipo e = (Equipo)it.next();
            tabla.addItem(new Object[]{i, e.getNombre(), e.getPuntos()}, i);
            i++;
        }
        tabla.setPageLength(listadoEquipos.size());
        
        Integer idPais = ligadao.buscarIdLigaPais(pais);
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +"/VAADIN/themes/tests-valo-dark/ligas/"+idPais+".png"));
        Image logo = new Image("", resource);
        logo.setWidth("200px");
        logo.setHeight("190px");
        verticalLayout.removeAllComponents();
        verticalLayout.addComponents(logo,tabla);
        verticalLayout.setComponentAlignment(logo, Alignment.TOP_CENTER);
    }
    
    public static void mostrarEquiposPais(VerticalLayout verticalLayout, String nombre){
        verticalLayout.setMargin(true);
        LigaDAO ligadao = new LigaDAO();
        Integer idLiga = ligadao.buscarIdLigaNombre(nombre);
        EquipoDAO equipodao = new EquipoDAO();
        List<Equipo> equipos = equipodao.getEquiposIdLiga(idLiga);
        int filas = (int)Math.ceil((equipos.size()/4));
        if(filas == 0){
            filas = 1;
        }
        GridLayout gridEquipos = new GridLayout(4, filas);
        gridEquipos.setSizeFull();
        gridEquipos.setSpacing(true);
        Panel panel;
        
        Iterator it = equipos.iterator();
        while(it.hasNext()){
            Equipo e = (Equipo)it.next();
            panel = new Panel("<center>"+e.getNombre()+"</center>");
            VerticalLayout content = new VerticalLayout();
            String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            FileResource resource = new FileResource(new File(basepath +"/VAADIN/themes/tests-valo-dark/equipos/"+e.getId()+".png"));
            Image logo = new Image("", resource);
            logo.setWidth("200px");
            logo.setHeight("190px");
            content.addComponent(logo);
            content.setComponentAlignment(logo, Alignment.TOP_CENTER);
            panel.setContent(content);
            gridEquipos.addComponents(panel);
        }
        verticalLayout.removeAllComponents();
        verticalLayout.addComponent(gridEquipos);
    }
        
    public static void mostrarJugadoresEquipo(VerticalLayout verticalLayout, String nombre){
        verticalLayout.setMargin(true);
        EquipoDAO equipodao = new EquipoDAO();
        Integer idEquipo = equipodao.buscarIdEquipoNombre(nombre);
        JugadorDAO jugadordao = new JugadorDAO();
        List<Jugador> jugadores = jugadordao.getJugadoresIdEquipo(idEquipo);
        int filas = (int)Math.ceil((jugadores.size()/4));
        if(filas == 0){
            filas = 1;
        }
        GridLayout gridJugadores = new GridLayout(4, filas);
        gridJugadores.setSizeFull();
        gridJugadores.setSpacing(true);
        Panel panel;

        Iterator it = jugadores.iterator();
        while(it.hasNext()){
            Jugador j = (Jugador)it.next();
            panel = new Panel("<center>"+j.getNombre()+"</center>");
            VerticalLayout content = new VerticalLayout();
            String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            FileResource resource = new FileResource(new File(basepath +"/VAADIN/themes/tests-valo-dark/jugadores/"+j.getId()+".jpg"));
            Image logo = new Image("", resource);
            logo.setWidth("130px");
            logo.setHeight("190px");
            Label posicion = new Label("POSICIÓN: "+j.getPosicion());
            Label nacionalidad = new Label("NACIONALIDAD: "+j.getNacionalidad());
            Label edad = new Label("EDAD: "+j.getEdad());
            content.addComponents(logo,posicion,edad,nacionalidad);
            content.setComponentAlignment(logo, Alignment.TOP_CENTER);
            content.setMargin(true);
            panel.setContent(content);
            gridJugadores.addComponents(panel);
        }
        
        verticalLayout.removeAllComponents();
        verticalLayout.addComponent(gridJugadores);
        
    }
    
    @WebServlet(urlPatterns = "/*", name = "MainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MainServlet extends VaadinServlet {
    }
}
