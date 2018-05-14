/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad;

import com.mycompany.leaguetad.dao.LigaDAO;
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
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import java.io.File;
import java.util.Iterator;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author expositod
 */
@Theme("tests-valo-dark")
public class Dashboard extends UI {

    String nombreLigaSelected = new String();
    static Button buttonCalendario = new Button("Ir a Calendario");
    static Button buttonJornada = new Button("Ir a Jornada");
    static Button buttonPartido = new Button("Ir a Partido");
    static Button buttonEquipo = new Button("Ir a Equipo");
    static Button buttonJugador = new Button("Ir a Jugador");
    static Button buttonTecnico = new Button("Ir a Técnico");

    @Override
    protected void init(VaadinRequest request) {
        VerticalSplitPanel layout = new VerticalSplitPanel();
        HorizontalLayout menuHorizontal = new HorizontalLayout();
        HorizontalLayout menuLigas = new HorizontalLayout();
        layout.setStyleName("fondo");
        MenuBar menu = new MenuBar();

        // A feedback component
        final Label selection = new Label("");
        menuHorizontal.addComponent(selection);
        // Define a common menu command for all the menu items.
        MenuBar.Command mycommand = new MenuBar.Command() {
            public void menuSelected(MenuItem selectedItem) {
                getPage().setLocation("/clasificacion");
            }
        };
        // A top-level menu item that opens a submenu
        MenuItem ligas = menu.addItem("Ligas", null, null);
        MenuItem clasificacion = menu.addItem("Clasificación", null, mycommand);

        LigaDAO ligadao = new LigaDAO();
        final Liga[] listadoLigas = ligadao.getLigas();

        GridLayout gridLigas = new GridLayout(listadoLigas.length, 1);
        gridLigas.setSpacing(true);
        Panel panel;
        Button buttonSantander = new Button("Ir a Liga Santander");
        Button buttonPremier = new Button("Ir a Premier League");
        Button buttonCalcio = new Button("Ir a Lega Calcio");

        for (int i = 0; i < listadoLigas.length; i++) {
            Liga l = (Liga) listadoLigas[i];
            ligas.addItem(l.getNombre(), null, null);
            panel = new Panel("<center>" + l.getNombre() + "</center>");
            panel.setWidth("280px");
            VerticalLayout content = new VerticalLayout();
            String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            FileResource resourceSantander = new FileResource(new File(basepath + "/VAADIN/themes/tests-valo-dark/ligas/" + l.getId() + ".png"));
            Image logo = new Image("", resourceSantander);
            logo.setWidth("200px");
            logo.setHeight("190px");
            content.addComponent(logo);
            if (l.getNombre().equals("Liga Santander")) {
                content.addComponent(buttonSantander);
                content.setComponentAlignment(buttonSantander, Alignment.MIDDLE_CENTER);
            } else if (l.getNombre().equals("Premier League")) {
                content.addComponent(buttonPremier);
                content.setComponentAlignment(buttonPremier, Alignment.MIDDLE_CENTER);
            } else if (l.getNombre().equals("Lega Calcio")) {
                content.addComponent(buttonCalcio);
                content.setComponentAlignment(buttonCalcio, Alignment.MIDDLE_CENTER);
            }
            content.setMargin(true);
            content.setComponentAlignment(logo, Alignment.TOP_CENTER);
            panel.setContent(content);
            gridLigas.addComponents(panel);
        }

        buttonSantander.addClickListener(e -> {
            menuLigas.removeAllComponents();
            nombreLigaSelected = "Liga Santander";
            mostrarMenuDashboard(menuLigas);
        });
        
        buttonPremier.addClickListener(e -> {
            menuLigas.removeAllComponents();
            nombreLigaSelected = "Premier League";
            mostrarMenuDashboard(menuLigas);
        });
        
        buttonCalcio.addClickListener(e -> {
            menuLigas.removeAllComponents();
            nombreLigaSelected = "Lega Calcio";
            mostrarMenuDashboard(menuLigas);
        });

        menuHorizontal.addComponents(selection, menu);
        menuLigas.addComponent(gridLigas);
        menuLigas.setSizeFull();
        menuLigas.setComponentAlignment(gridLigas, Alignment.MIDDLE_CENTER);
        layout.addComponents(menuHorizontal, menuLigas);
        layout.setSplitPosition(10);
        layout.setLocked(true);
        layout.setSizeFull();
        setContent(layout);
    }

    public static void mostrarMenuDashboard(HorizontalLayout menuLigas) {
        GridLayout gridDashboard = new GridLayout(4, 2);
        gridDashboard.setSizeFull();
        gridDashboard.setSpacing(true);
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        //Calendario
        Panel panelCalendario = new Panel("<center>CALENDARIO</center>");
        VerticalLayout contentCalendario = new VerticalLayout();
        FileResource resourceCalendario = new FileResource(new File(basepath + "/VAADIN/themes/tests-valo-dark/dashboard/calendario.png"));
        Image logoCalendario = new Image("", resourceCalendario);
        logoCalendario.setWidth("130px");
        logoCalendario.setHeight("130px");
        contentCalendario.addComponents(logoCalendario,buttonCalendario);
        contentCalendario.setComponentAlignment(logoCalendario, Alignment.TOP_CENTER);
        contentCalendario.setComponentAlignment(buttonCalendario, Alignment.MIDDLE_CENTER);
        contentCalendario.setMargin(true);
        panelCalendario.setContent(contentCalendario);
        gridDashboard.addComponent(panelCalendario);

        //Jornada
        Panel panelJornada = new Panel("<center>JORNADAS</center>");
        VerticalLayout contentJornada = new VerticalLayout();
        FileResource resourceJornada = new FileResource(new File(basepath + "/VAADIN/themes/tests-valo-dark/dashboard/jornada.png"));
        Image logoJornada = new Image("", resourceJornada);
        logoJornada.setWidth("130px");
        logoJornada.setHeight("130px");
        contentJornada.addComponents(logoJornada,buttonJornada);
        contentJornada.setComponentAlignment(logoJornada, Alignment.TOP_CENTER);
        contentJornada.setComponentAlignment(buttonJornada, Alignment.MIDDLE_CENTER);
        contentJornada.setMargin(true);
        panelJornada.setContent(contentJornada);
        gridDashboard.addComponent(panelJornada);

        //Partido
        Panel panelPartido = new Panel("<center>PARTIDOS</center>");
        VerticalLayout contentPartido = new VerticalLayout();
        FileResource resourcePartido = new FileResource(new File(basepath + "/VAADIN/themes/tests-valo-dark/dashboard/partido.png"));
        Image logoPartido = new Image("", resourcePartido);
        logoPartido.setWidth("130px");
        logoPartido.setHeight("130px");
        contentPartido.addComponents(logoPartido,buttonPartido);
        contentPartido.setComponentAlignment(logoPartido, Alignment.TOP_CENTER);
        contentPartido.setComponentAlignment(buttonPartido, Alignment.MIDDLE_CENTER);
        contentPartido.setMargin(true);
        panelPartido.setContent(contentPartido);
        gridDashboard.addComponent(panelPartido);

        //Equipo
        Panel panelEquipo = new Panel("<center>EQUIPOS</center>");
        VerticalLayout contentEquipo = new VerticalLayout();
        FileResource resourceEquipo = new FileResource(new File(basepath + "/VAADIN/themes/tests-valo-dark/dashboard/equipo.png"));
        Image logoEquipo = new Image("", resourceEquipo);
        logoEquipo.setWidth("130px");
        logoEquipo.setHeight("130px");
        contentEquipo.addComponents(logoEquipo,buttonEquipo);
        contentEquipo.setComponentAlignment(logoEquipo, Alignment.TOP_CENTER);
        contentEquipo.setComponentAlignment(buttonEquipo, Alignment.MIDDLE_CENTER);
        contentEquipo.setMargin(true);
        panelEquipo.setContent(contentEquipo);
        gridDashboard.addComponent(panelEquipo);

        //Jugador
        Panel panelJugador = new Panel("<center>JUGADOR</center>");
        VerticalLayout contentJugador = new VerticalLayout();
        FileResource resourceJugador = new FileResource(new File(basepath + "/VAADIN/themes/tests-valo-dark/dashboard/jugador.png"));
        Image logoJugador = new Image("", resourceJugador);
        logoJugador.setWidth("130px");
        logoJugador.setHeight("130px");
        contentJugador.addComponents(logoJugador,buttonJugador);
        contentJugador.setComponentAlignment(logoJugador, Alignment.TOP_CENTER);
        contentJugador.setComponentAlignment(buttonJugador, Alignment.MIDDLE_CENTER);
        contentJugador.setMargin(true);
        panelJugador.setContent(contentJugador);
        gridDashboard.addComponent(panelJugador);

        //Tecnico
        Panel panelTecnico = new Panel("<center>TECNICO</center>");
        VerticalLayout contentTecnico = new VerticalLayout();
        FileResource resourceTecnico = new FileResource(new File(basepath + "/VAADIN/themes/tests-valo-dark/dashboard/tecnico.png"));
        Image logoTecnico = new Image("", resourceTecnico);
        logoTecnico.setWidth("130px");
        logoTecnico.setHeight("130px");
        contentTecnico.addComponents(logoTecnico,buttonTecnico);
        contentTecnico.setComponentAlignment(logoTecnico, Alignment.TOP_CENTER);
        contentTecnico.setComponentAlignment(buttonTecnico, Alignment.MIDDLE_CENTER);
        contentTecnico.setMargin(true);
        panelTecnico.setContent(contentTecnico);
        gridDashboard.addComponent(panelTecnico);

        menuLigas.addComponent(gridDashboard);
        menuLigas.setMargin(true);
        gridDashboard.setSizeFull();
    }

    @WebServlet(urlPatterns = "/dashboard/*", name = "DashboardServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Dashboard.class, productionMode = false)
    public static class DashboardServlet extends VaadinServlet {
    }
}
