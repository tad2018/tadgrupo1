/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad;

import com.mycompany.leaguetad.dao.CalendarioDAO;
import com.mycompany.leaguetad.dao.JornadaDAO;
import com.mycompany.leaguetad.dao.LigaDAO;
import com.mycompany.leaguetad.persistence.Calendario;
import com.mycompany.leaguetad.persistence.Equipo;
import com.mycompany.leaguetad.persistence.Jornada;
import com.mycompany.leaguetad.persistence.Liga;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author expositod
 */
@Theme("tests-valo-dark")
public class Dashboard extends UI {

    static String nombreLigaSelected = new String();
    static Button buttonCalendario = new Button("Ir a Calendario");
    static Button buttonJornada = new Button("Ir a Jornada");
    static Button buttonPartido = new Button("Ir a Partido");
    static Button buttonEquipo = new Button("Ir a Equipo");
    static Button buttonJugador = new Button("Ir a Jugador");
    static Button buttonTecnico = new Button("Ir a Técnico");
    static Button buttonCrearCalendario = new Button("Crear Calendario");
    static Button buttonActualizarCalendario = new Button("Actualizar Calendario");
    static Button buttonBorrarCalendario = new Button("Borrar Calendario");
    static Button buttonCrearJornada = new Button("Crear Jornada");
    static Button buttonCrearJugador = new Button("Crear Jugador");
    static Button buttonCrearTecnico = new Button("Crear Técnico");
    static TextField fieldAnyoCalendario = new TextField("Año Calendario");
    final static Table tablaCalendario = new Table();
    static FormLayout formCalendario = new FormLayout();
    static Calendario calendarioSeleccionado = new Calendario();
    static List<Calendario> calendarios = new ArrayList<>();
    static TextField fieldNumeroJornada = new TextField("Número de la Jornada");
    static ComboBox selectCalendario = new ComboBox("Calendarios");
    static DateField fieldfechaJornada = new DateField("Fecha Jornada");
    final static Table tablaJornada = new Table();
    static FormLayout formJornada = new FormLayout();
    static List<Jornada> jornadas = new ArrayList<>();
    static Jornada jornadaSeleccionada = new Jornada();

    @Override
    protected void init(VaadinRequest request) {
        VerticalSplitPanel layout = new VerticalSplitPanel();
        HorizontalLayout menuHorizontal = new HorizontalLayout();
        HorizontalLayout menuLigas = new HorizontalLayout();
        layout.setStyleName("fondo");
        MenuBar menu = new MenuBar();
        
        tablaCalendario.setSelectable(true);
        tablaJornada.setSelectable(true);

        // A feedback component
        final Label selection = new Label("");
        menuHorizontal.addComponent(selection);
        // Define a common menu command for all the menu items.
        MenuBar.Command mycommand = new MenuBar.Command() {
            public void menuSelected(MenuItem selectedItem) {
                getPage().setLocation("/clasificacion");
            }
        };
        
        MenuBar.Command mycommandDashboard = new MenuBar.Command() {
            public void menuSelected(MenuItem selectedItem) {
                getPage().setLocation("/dashboard");
            }
        };
        
        MenuBar.Command mycommandLiga = new MenuBar.Command() {
            public void menuSelected(MenuItem selectedItem) {
                getPage().setLocation("/dashboard");
            }
        };
        // A top-level menu item that opens a submenu
        MenuItem ligas = menu.addItem("Ligas", null, null);
        MenuItem clasificacion = menu.addItem("Clasificación", null, mycommand);
        MenuItem dashboard = menu.addItem("Dashboard", null, mycommandDashboard);

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
            ligas.addItem(l.getNombre(), new Command() {
                public void menuSelected(MenuItem selectedItem) {
                    if(l.getNombre().equals("Liga Santander")){
                        menuLigas.removeAllComponents();
                        nombreLigaSelected = "Liga Santander";
                        mostrarMenuDashboard(menuLigas);
                    }
                    else if(l.getNombre().equals("Premier League")){
                        menuLigas.removeAllComponents();
                        nombreLigaSelected = "Premier League";
                        mostrarMenuDashboard(menuLigas);
                    }
                    else if(l.getNombre().equals("Lega Calcio")){
                        menuLigas.removeAllComponents();
                        nombreLigaSelected = "Lega Calcio";
                        mostrarMenuDashboard(menuLigas);
                    }
                }
            });
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
        
        buttonCalendario.addClickListener(e -> {
            menuLigas.removeAllComponents();
            calendarios = mostrarTablaCalendario(menuLigas);
        });
        
        buttonCrearCalendario.addClickListener(e -> {
            menuLigas.removeAllComponents();
            try {
                crearCalendario();
            } catch (ParseException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            mostrarTablaCalendario(menuLigas);
        });
        
        buttonActualizarCalendario.addClickListener(e -> {
            menuLigas.removeAllComponents();
            CalendarioDAO calendariodao = new CalendarioDAO();
            String anyo = fieldAnyoCalendario.getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(anyo);
            } catch (ParseException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            calendarioSeleccionado.setAnyo(timestamp);
            calendariodao.actualizarCalendario(calendarioSeleccionado);
            mostrarTablaCalendario(menuLigas);
        });
        
        buttonBorrarCalendario.addClickListener(e -> {
            menuLigas.removeAllComponents();
            CalendarioDAO calendariodao = new CalendarioDAO();
            calendariodao.borrarCalendario(calendarioSeleccionado);
            mostrarTablaCalendario(menuLigas);
        });
        
        buttonCrearCalendario.addClickListener(e -> {
            menuLigas.removeAllComponents();
            try {
                crearCalendario();
            } catch (ParseException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            mostrarTablaCalendario(menuLigas);
        });
        
        this.tablaCalendario.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                int calendarioSelec = (Integer) event.getItemId() - 1;
                Calendario calendario = calendarios.get(calendarioSelec);
                Object value = event.getItem().getItemProperty("AÑO").getValue();
                fieldAnyoCalendario.setValue(value.toString());
                calendarioSeleccionado =  calendario;
            }
        });
        
        
        buttonJornada.addClickListener(e -> {
            menuLigas.removeAllComponents();
            jornadas = mostrarTablaJornada(menuLigas);
        });
        
        buttonCrearJornada.addClickListener(e -> {
            menuLigas.removeAllComponents();
            try {
                crearJornada();
            } catch (ParseException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            mostrarTablaJornada(menuLigas);
        });
        
        this.tablaJornada.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            public void itemClick(ItemClickEvent event) {
                int jornadaSelec = (Integer) event.getItemId() - 1;
                Jornada jornada = jornadas.get(jornadaSelec);
                selectCalendario.setValue(jornada.getCalendarioByCalendarioId());
                fieldNumeroJornada.setValue(String.valueOf(jornada.getNumero()));
                fieldfechaJornada.setValue(jornada.getFecha());
                jornadaSeleccionada = jornada;
            }
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
        contentCalendario.setComponentAlignment(buttonCalendario, Alignment.BOTTOM_CENTER);
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
        contentJornada.setComponentAlignment(buttonJornada, Alignment.BOTTOM_CENTER);
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
        contentPartido.setComponentAlignment(buttonPartido, Alignment.BOTTOM_CENTER);
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
        contentEquipo.setComponentAlignment(buttonEquipo, Alignment.BOTTOM_CENTER);
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
        contentJugador.setComponentAlignment(buttonJugador, Alignment.BOTTOM_CENTER);
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
        contentTecnico.setComponentAlignment(buttonTecnico, Alignment.BOTTOM_CENTER);
        contentTecnico.setMargin(true);
        panelTecnico.setContent(contentTecnico);
        gridDashboard.addComponent(panelTecnico);

        menuLigas.addComponent(gridDashboard);
        menuLigas.setMargin(true);
        gridDashboard.setSizeFull();
    }
    
    public static List<Calendario> mostrarTablaCalendario(HorizontalLayout menuLigas) {
        menuLigas.removeAllComponents();
        LigaDAO ligadao = new LigaDAO();
        //Tabla
        tablaCalendario.removeAllItems();
        tablaCalendario.setWidth(100, UNITS_PERCENTAGE);
        tablaCalendario.setSelectable(true);
        tablaCalendario.setMultiSelect(false);
        tablaCalendario.setImmediate(true);
        tablaCalendario.addContainerProperty("AÑO", Integer.class, null);
        
        CalendarioDAO calendariodao = new CalendarioDAO();
        Liga liga = ligadao.buscarLigaporNombre(nombreLigaSelected);
        List<Calendario> calendarios = calendariodao.getCalendarios(liga.getId());
        Iterator it1 = calendarios.iterator();
        int i = 1;
        while(it1.hasNext()){
            Calendario c = (Calendario)it1.next();
            long timestamp = c.getAnyo().getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);
            tablaCalendario.addItem(new Object[]{cal.get(Calendar.YEAR)}, i);
            i++;
        }
        tablaCalendario.setPageLength(calendarios.size());
        
        
        //Formulario
        formCalendario = new FormLayout();
        formCalendario.setSizeUndefined();
        formCalendario.addComponents(fieldAnyoCalendario,buttonCrearCalendario,buttonActualizarCalendario,buttonBorrarCalendario);
        formCalendario.setStyleName("formCalendario");
        formCalendario.setMargin(true);
        menuLigas.addComponents(tablaCalendario,formCalendario);
        menuLigas.setSpacing(true);
        return calendarios;
    }
    
    public static List<Jornada> mostrarTablaJornada(HorizontalLayout menuLigas) {
        menuLigas.removeAllComponents();
        JornadaDAO jornadadao = new JornadaDAO();
        //Tabla
        tablaJornada.setWidth(100, UNITS_PERCENTAGE);
        tablaJornada.setSelectable(true);
        tablaJornada.setMultiSelect(false);
        tablaJornada.setImmediate(true);
        tablaJornada.addContainerProperty("NÚMERO", Integer.class, null);
        tablaJornada.addContainerProperty("FECHA", String.class, null);
        tablaJornada.addContainerProperty("CALENDARIO", Integer.class, null);
        
        LigaDAO ligadao = new LigaDAO();
        Liga liga = ligadao.buscarLigaporNombre(nombreLigaSelected);
        CalendarioDAO calendariodao = new CalendarioDAO();
        List<Calendario> calendarios = calendariodao.getCalendarios(liga.getId());
        Iterator it = calendarios.iterator();
        int i = 1;
        List<Jornada> returnJornadas = new ArrayList<Jornada>();
        while(it.hasNext()){
            Calendario c = (Calendario)it.next();
            long timestamp = c.getAnyo().getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);
            List<Jornada> listaJornadas = jornadadao.getJornadas(c.getId());
            Iterator it1 = listaJornadas.iterator();
            while(it1.hasNext()){
                Jornada j = (Jornada)it1.next();
                returnJornadas.add(j);
                tablaJornada.addItem(new Object[]{j.getNumero(),new SimpleDateFormat("dd/MM/yyyy").format(j.getFecha()),cal.get(Calendar.YEAR)}, i);
                i++;
            }
            selectCalendario.addItem(c);
            selectCalendario.setItemCaption(c, String.valueOf(cal.get(Calendar.YEAR)));
            selectCalendario.setNullSelectionAllowed(false);
        }
        tablaJornada.setPageLength(i-1);
        
        
        //Formulario
        formJornada = new FormLayout();
        formJornada.setSizeUndefined();
        formJornada.addComponents(fieldNumeroJornada,selectCalendario,fieldfechaJornada,buttonCrearJornada);
        formJornada.setStyleName("formCalendario");
        formJornada.setMargin(true);
        menuLigas.addComponents(tablaJornada,formJornada);
        menuLigas.setSpacing(true);
        return returnJornadas;
    }
    
    public static void crearCalendario() throws ParseException{
        String anyo = fieldAnyoCalendario.getValue();
        if (!anyo.equals("")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            Date parsedDate = dateFormat.parse(anyo);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            LigaDAO ligadao = new LigaDAO();
            Liga liga = ligadao.buscarLigaporNombre(nombreLigaSelected);
            Calendario calendario = new Calendario();
            calendario.setAnyo(timestamp);
            calendario.setLigaId(liga);
            CalendarioDAO calendariodao = new CalendarioDAO();
            calendariodao.crearCalendario(calendario);
            fieldAnyoCalendario.setValue("");
        }
        else{
            Notification n = new Notification("Enter the fields",Notification.Type.ERROR_MESSAGE);
            n.setDelayMsec(1000);
            n.setPosition(Notification.POSITION_CENTERED_TOP);
            n.show(Page.getCurrent());
        }
    }
    
    public static void crearJornada() throws ParseException{
        Integer numero = Integer.parseInt(fieldNumeroJornada.getValue());
        Calendario c = (Calendario)selectCalendario.getValue();
        Date fecha = fieldfechaJornada.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp timestamp = new java.sql.Timestamp(fecha.getTime());
        if (!numero.equals("")){
            LigaDAO ligadao = new LigaDAO();
            Liga liga = ligadao.buscarLigaporNombre(nombreLigaSelected);
            Jornada jornada = new Jornada();
            jornada.setNumero(numero);
            jornada.setCalendarioByCalendarioId(c);
            jornada.setFecha(timestamp);
            JornadaDAO jornadadao = new JornadaDAO();
            jornadadao.crearJornada(jornada);
            fieldNumeroJornada.setValue("");
        }
        else{
            Notification n = new Notification("Enter the fields",Notification.Type.ERROR_MESSAGE);
            n.setDelayMsec(1000);
            n.setPosition(Notification.POSITION_CENTERED_TOP);
            n.show(Page.getCurrent());
        }
    }

    @WebServlet(urlPatterns = "/dashboard/*", name = "DashboardServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Dashboard.class, productionMode = false)
    public static class DashboardServlet extends VaadinServlet {
    }
}
