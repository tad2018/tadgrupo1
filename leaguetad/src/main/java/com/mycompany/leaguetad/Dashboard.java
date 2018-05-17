/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad;

import com.mycompany.leaguetad.dao.CalendarioDAO;
import com.mycompany.leaguetad.dao.EquipoDAO;
import com.mycompany.leaguetad.dao.EquipoTecnicoDAO;
import com.mycompany.leaguetad.dao.JornadaDAO;
import com.mycompany.leaguetad.dao.JugadorDAO;
import com.mycompany.leaguetad.dao.LigaDAO;
import com.mycompany.leaguetad.dao.PartidoDAO;
import com.mycompany.leaguetad.persistence.Calendario;
import com.mycompany.leaguetad.persistence.Equipo;
import com.mycompany.leaguetad.persistence.Jornada;
import com.mycompany.leaguetad.persistence.Jugador;
import com.mycompany.leaguetad.persistence.Liga;
import com.mycompany.leaguetad.persistence.Tecnico;
import com.mycompany.leaguetad.persistence.Partido;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
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
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

/**
 * @author expositod
 */
@Theme("tests-valo-dark")
public class Dashboard extends UI {

    static String nombreLigaSelected = new String();
    
    static Button buttonAsignarPartido = new Button("AsignarPartido");
    
    //Componentes calendario
    static Button buttonCalendario = new Button("Ir a Calendario");
    static Button buttonCrearCalendario = new Button("Crear Calendario");
    static Button buttonActualizarCalendario = new Button("Actualizar Calendario");
    static Button buttonBorrarCalendario = new Button("Borrar Calendario");
    
    /* Calendario */
    static TextField fieldAnyoCalendario = new TextField("Año Calendario");
    final static Table tablaCalendario = new Table();
    static FormLayout formCalendario = new FormLayout();
    static Calendario calendarioSeleccionado = new Calendario();
    static List<Calendario> calendarios = new ArrayList<>();
    
    /* Jornada */
    static TextField fieldNumeroJornada = new TextField("Número de la Jornada");
    static ComboBox selectCalendario = new ComboBox("Calendarios");
    static DateField fieldfechaJornada = new DateField("Fecha Jornada");
    
    //Componentes jornada
    static Button buttonCrearJornada = new Button("Crear Jornada");
    static Button buttonActualizarJornada = new Button("Actualizar Jornada");
    static Button buttonBorrarJornada = new Button("Borrar Jornada");
    static Button buttonJornada = new Button("Ir a Jornada");
    final static Table tablaJornada = new Table();
    static FormLayout formJornada = new FormLayout();
    static List<Jornada> jornadas = new ArrayList<>();
    static Jornada jornadaSeleccionada = new Jornada();
    
    //Componentes partido
    static Button buttonPartido = new Button("Ir a Partido");
    static Button buttonCrearPartido = new Button("Crear Partido");
    static Button buttonActualizarPartido = new Button("Actualizar Partido");
    static Button buttonBorrarPartido = new Button("Borrar Partido");
    final static Table tablaPartido = new Table();
    final static Table tablaPartidoNull = new Table();
    static List<Partido> partidos = new ArrayList<>();
    static List<Partido> partidosNull = new ArrayList<>();    
    static FormLayout formPartido = new FormLayout();
    static ComboBox selectEquipoLocal = new ComboBox("Equipo Local");
    static ComboBox selectEquipoVisitante = new ComboBox("Equipo Visitante");
    static Partido partidoSeleccionado = new Partido();
    static Partido partidoSeleccionadoNull = new Partido();
    static TabSheet tabPartido = new TabSheet();
    static VerticalLayout layoutPartido = new VerticalLayout();
    
    //Componentes equipo
    static Button buttonEquipo = new Button("Ir a Equipo");
    
    //Componentes jugador
    static Button buttonJugador = new Button("Ir a Jugador");
    static Button buttonCrearJugador = new Button("Crear Jugador");
    static Button buttonActualizarJugador = new Button("Actualizar Jugador");
    static Button buttonBorrarJugador = new Button("Borrar Jugador");
    
    //Componentes equipo tecnico
    static Button buttonTecnico = new Button("Ir a Técnico");
    static Button buttonCrearTecnico = new Button("Crear Técnico");
    static Button buttonActualizarTecnico = new Button("Actualizar Técnico");
    static Button buttonBorrarTecnico = new Button("Borrar Técnico");
    
    /* Equipo */
    final static Table tablaEquipo = new Table();
    
    /*Jugador*/
    final static Table tablaJugador = new Table();
    static FormLayout formJugador = new FormLayout();
    static FormLayout formJugador2 = new FormLayout();
    static List<Jugador> jugadores = new ArrayList<>();
    static TextField fieldNombreJugador = new TextField("Nombre");
    static TextField fieldNacionalidadJugador = new TextField("Nacionalidad");
    static TextField fieldPosicionJugador = new TextField("Posición");
    static TextField fieldEdadJugador = new TextField("Edad");
    static TextField fieldGolesJugador = new TextField("Goles");
    static TextField fieldPasesJugador = new TextField("Pases");
    static TextField fieldFaltasJugador = new TextField("Faltas");
    static TextField fieldExpulsionesJugador = new TextField("Expulsiones");
    static TextField fieldParadasJugador = new TextField("Paradas");
    static TextField fieldTirosJugador = new TextField("Tiros");
    static ComboBox selectEquipo = new ComboBox("Equipos");
    static Jugador jugadorSeleccionado = new Jugador();
    
    /*Tecnico*/
    final static Table tablaTecnico = new Table();
    static FormLayout formTecnico = new FormLayout();
    static List<Tecnico> tecnicos = new ArrayList<>();
    static TextField fieldNombreTecnico = new TextField("Nombre");
    static TextField fieldPuestoTecnico = new TextField("Puesto");
    static ComboBox selectEquipoTecnico = new ComboBox("Equipos");
    static Tecnico tecnicoSeleccionado = new Tecnico();

    @Override
    protected void init(VaadinRequest request) {
        WrappedSession session = getSession().getSession();
        if(session.getAttribute("user")==null){
            getPage().setLocation("/admin");
        }
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
        
         MenuBar.Command mycommandSesion = new MenuBar.Command() {
            public void menuSelected(MenuItem selectedItem) {
                session.invalidate();
                getPage().setLocation("/admin");
            }
        };
        // A top-level menu item that opens a submenu
        MenuItem ligas = menu.addItem("Ligas", null, null);
        MenuItem clasificacion = menu.addItem("Clasificación", null, mycommand);
        MenuItem dashboard = menu.addItem("Dashboard", null, mycommandDashboard);
        MenuItem cerrarSesion = menu.addItem("Cerrar Sesion", null, mycommandSesion);

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
                    if (l.getNombre().equals("Liga Santander")) {
                        menuLigas.removeAllComponents();
                        nombreLigaSelected = "Liga Santander";
                        mostrarMenuDashboard(menuLigas);
                    } else if (l.getNombre().equals("Premier League")) {
                        menuLigas.removeAllComponents();
                        nombreLigaSelected = "Premier League";
                        mostrarMenuDashboard(menuLigas);
                    } else if (l.getNombre().equals("Lega Calcio")) {
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
            FileResource resourceSantander = new FileResource(new File(
                    basepath + "/VAADIN/themes/tests-valo-dark/ligas/" + l.getId() + ".png"));
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
        
        //CALENDARIO
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

        this.tablaCalendario.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                int calendarioSelec = (Integer) event.getItemId() - 1;
                Calendario calendario = calendarios.get(calendarioSelec);
                Object value = event.getItem().getItemProperty("AÑO").getValue();
                fieldAnyoCalendario.setValue(value.toString());
                calendarioSeleccionado = calendario;
            }
        });
        
        //JORNADA
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
            jornadas = mostrarTablaJornada(menuLigas);
        });

        buttonActualizarJornada.addClickListener(e -> {
            menuLigas.removeAllComponents();
            JornadaDAO jornadadao = new JornadaDAO();

            Calendario calendario = (Calendario) selectCalendario.getValue();
            int numero = Integer.parseInt(fieldNumeroJornada.getValue());
            Date fecha = fieldfechaJornada.getValue();
            jornadaSeleccionada.setCalendarioByCalendarioId(calendario);
            jornadaSeleccionada.setNumero(numero);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Timestamp timestamp = new java.sql.Timestamp(fecha.getTime());
            jornadaSeleccionada.setFecha(timestamp);
            jornadadao.actualizarJornada(jornadaSeleccionada);
            jornadas = mostrarTablaJornada(menuLigas);
        });

        buttonBorrarJornada.addClickListener(e -> {
            menuLigas.removeAllComponents();
            JornadaDAO jornadadao = new JornadaDAO();
            jornadadao.borrarJornada(jornadaSeleccionada);
            jornadas = mostrarTablaJornada(menuLigas);
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
        
        //PARTIDO
        buttonPartido.addClickListener(e -> {
            menuLigas.removeAllComponents();
            partidosNull = mostrarTablaPartidosNull(menuLigas);
            partidos = mostrarTablaPartidos(menuLigas);
        });
        
        buttonCrearPartido.addClickListener(e -> {
            menuLigas.removeAllComponents();
            try {
                crearPartido();
            } catch (ParseException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            partidosNull = mostrarTablaPartidosNull(menuLigas);
            partidos = mostrarTablaPartidos(menuLigas);
        });
        
        buttonActualizarPartido.addClickListener(e -> {
            menuLigas.removeAllComponents();
            PartidoDAO partidoDAO = new PartidoDAO();
            Equipo eLocal = (Equipo) selectEquipoLocal.getValue();
            Equipo eVisitante = (Equipo) selectEquipoVisitante.getValue();
            
            partidoSeleccionado.setEquipoByLocalId(eLocal);
            partidoSeleccionado.setEquipoByVisitanteId(eVisitante);
            partidoDAO.actualizarPartido(partidoSeleccionado);
            
            partidosNull = mostrarTablaPartidosNull(menuLigas);
            partidos = mostrarTablaPartidos(menuLigas);
        });
        
        buttonBorrarPartido.addClickListener(e -> {
            menuLigas.removeAllComponents();
            PartidoDAO partidoDAO = new PartidoDAO();
            partidoDAO.borrarPartido(partidoSeleccionado);
            partidosNull = mostrarTablaPartidosNull(menuLigas);
            partidos = mostrarTablaPartidos(menuLigas);
        });
        
        this.tablaPartido.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            public void itemClick(ItemClickEvent event) {
                int partidoSelec = (Integer) event.getItemId() - 1;
                Partido partido = partidos.get(partidoSelec);
                Equipo e1 = partido.getEquipoByLocalId();
                Equipo e2 = partido.getEquipoByVisitanteId();
                selectEquipoLocal.setValue(e1);
                selectEquipoVisitante.setValue(e2);
                partidoSeleccionado = partido;
            }
        });
        
        this.tablaPartidoNull.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            public void itemClick(ItemClickEvent event) {
                int partidoSelec = (Integer) event.getItemId() - 1;
                Partido partido = partidosNull.get(partidoSelec);
                Equipo e1 = partido.getEquipoByLocalId();
                Equipo e2 = partido.getEquipoByVisitanteId();
                selectEquipoLocal.setValue(e1);
                selectEquipoVisitante.setValue(e2);
                partidoSeleccionado = partido;
            }
        });

        buttonEquipo.addClickListener(e -> {
            menuLigas.removeAllComponents();
            mostrarTablaEquipo(menuLigas);
        });
        
        buttonJugador.addClickListener(e -> {
            menuLigas.removeAllComponents();
            jugadores = mostrarTablaJugador(menuLigas);
        });
        
        this.tablaJugador.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            public void itemClick(ItemClickEvent event) {
                int jugadorSelec = (Integer) event.getItemId() - 1;
                Jugador jugador = jugadores.get(jugadorSelec);
                fieldNombreJugador.setValue(jugador.getNombre());
                fieldNacionalidadJugador.setValue(jugador.getNacionalidad());
                fieldPosicionJugador.setValue(jugador.getPosicion());
                fieldEdadJugador.setValue(String.valueOf(jugador.getEdad()));
                fieldGolesJugador.setValue(String.valueOf(jugador.getGoles()));
                fieldPasesJugador.setValue(String.valueOf(jugador.getPases()));
                fieldFaltasJugador.setValue(String.valueOf(jugador.getPases()));
                fieldExpulsionesJugador.setValue(String.valueOf(jugador.getExpulsiones()));
                fieldParadasJugador.setValue(String.valueOf(jugador.getParadas()));
                fieldTirosJugador.setValue(String.valueOf(jugador.getTiros()));
                selectEquipo.setValue(jugador.getEquipoByEquipoId());
                jugadorSeleccionado = jugador;
            }
        });
        
        buttonCrearJugador.addClickListener(e -> {
            menuLigas.removeAllComponents();
            try {
                crearJugador();
            } catch (ParseException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            jugadores = mostrarTablaJugador(menuLigas);
        });

        buttonActualizarJugador.addClickListener(e -> {
            menuLigas.removeAllComponents();
            JugadorDAO jugadordao = new JugadorDAO();
            jugadorSeleccionado.setNombre(fieldNombreJugador.getValue());
            jugadorSeleccionado.setNacionalidad(fieldNacionalidadJugador.getValue());
            jugadorSeleccionado.setPosicion(fieldPosicionJugador.getValue());
            jugadorSeleccionado.setEdad(Integer.parseInt(fieldEdadJugador.getValue()));
            jugadorSeleccionado.setGoles(Integer.parseInt(fieldGolesJugador.getValue()));
            jugadorSeleccionado.setPases(Integer.parseInt(fieldPasesJugador.getValue()));
            jugadorSeleccionado.setFaltas(Integer.parseInt(fieldFaltasJugador.getValue()));
            jugadorSeleccionado.setExpulsiones(Integer.parseInt(fieldExpulsionesJugador.getValue()));
            jugadorSeleccionado.setParadas(Integer.parseInt(fieldParadasJugador.getValue()));
            jugadorSeleccionado.setTiros(Integer.parseInt(fieldTirosJugador.getValue()));
            jugadorSeleccionado.setEquipoByEquipoId((Equipo)selectEquipo.getValue());
            jugadordao.actualizarJugador(jugadorSeleccionado);
            jugadores = mostrarTablaJugador(menuLigas);
        });

        buttonBorrarJugador.addClickListener(e -> {
            menuLigas.removeAllComponents();
            JugadorDAO jugadordao = new JugadorDAO();
            jugadordao.borrarJugador(jugadorSeleccionado);
            jugadores = mostrarTablaJugador(menuLigas);
        });
        
        buttonTecnico.addClickListener(e -> {
            menuLigas.removeAllComponents();
            tecnicos = mostrarTablaTecnico(menuLigas);
        });
        
        buttonAsignarPartido.addClickListener(e -> {
            getPage().setLocation("/calendarioLiga");
        });
        
        this.tablaTecnico.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            public void itemClick(ItemClickEvent event) {
                int tecnicoSelec = (Integer) event.getItemId() - 1;
                Tecnico tecnico = tecnicos.get(tecnicoSelec);
                fieldNombreTecnico.setValue(tecnico.getNombre());
                fieldPuestoTecnico.setValue(tecnico.getPuesto());
                selectEquipoTecnico.setValue(tecnico.getEquipoByEquipoId());
                tecnicoSeleccionado = tecnico;
            }
        });
        
        buttonCrearTecnico.addClickListener(e -> {
            menuLigas.removeAllComponents();
            try {
                crearTecnico();
            } catch (ParseException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            tecnicos = mostrarTablaTecnico(menuLigas);
        });

        buttonActualizarTecnico.addClickListener(e -> {
            menuLigas.removeAllComponents();
            EquipoTecnicoDAO tecnicodao = new EquipoTecnicoDAO();
            tecnicoSeleccionado.setNombre(fieldNombreTecnico.getValue());
            tecnicoSeleccionado.setPuesto(fieldPuestoTecnico.getValue());
            tecnicoSeleccionado.setEquipoByEquipoId((Equipo)selectEquipoTecnico.getValue());
            tecnicodao.actualizarTecnico(tecnicoSeleccionado);
            tecnicos = mostrarTablaTecnico(menuLigas);
        });

        buttonBorrarTecnico.addClickListener(e -> {
            menuLigas.removeAllComponents();
            EquipoTecnicoDAO tecnicodao = new EquipoTecnicoDAO();
            tecnicodao.borrarTecnico(tecnicoSeleccionado);
            tecnicos = mostrarTablaTecnico(menuLigas);
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
        FileResource resourceCalendario = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/dashboard/calendario.png"));
        Image logoCalendario = new Image("", resourceCalendario);
        logoCalendario.setWidth("130px");
        logoCalendario.setHeight("130px");
        contentCalendario.addComponents(logoCalendario, buttonCalendario);
        contentCalendario.setComponentAlignment(logoCalendario, Alignment.TOP_CENTER);
        contentCalendario.setComponentAlignment(buttonCalendario, Alignment.BOTTOM_CENTER);
        contentCalendario.setMargin(true);
        panelCalendario.setContent(contentCalendario);
        gridDashboard.addComponent(panelCalendario);

        //Jornada
        Panel panelJornada = new Panel("<center>JORNADAS</center>");
        VerticalLayout contentJornada = new VerticalLayout();
        FileResource resourceJornada = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/dashboard/jornada.png"));
        Image logoJornada = new Image("", resourceJornada);
        logoJornada.setWidth("130px");
        logoJornada.setHeight("130px");
        contentJornada.addComponents(logoJornada, buttonJornada);
        contentJornada.setComponentAlignment(logoJornada, Alignment.TOP_CENTER);
        contentJornada.setComponentAlignment(buttonJornada, Alignment.BOTTOM_CENTER);
        contentJornada.setMargin(true);
        panelJornada.setContent(contentJornada);
        gridDashboard.addComponent(panelJornada);

        //Partido
        Panel panelPartido = new Panel("<center>PARTIDOS</center>");
        VerticalLayout contentPartido = new VerticalLayout();
        FileResource resourcePartido = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/dashboard/partido.png"));
        Image logoPartido = new Image("", resourcePartido);
        logoPartido.setWidth("130px");
        logoPartido.setHeight("130px");
        contentPartido.addComponents(logoPartido, buttonPartido);
        contentPartido.setComponentAlignment(logoPartido, Alignment.TOP_CENTER);
        contentPartido.setComponentAlignment(buttonPartido, Alignment.BOTTOM_CENTER);
        contentPartido.setMargin(true);
        panelPartido.setContent(contentPartido);
        gridDashboard.addComponent(panelPartido);

        //Equipo
        Panel panelEquipo = new Panel("<center>EQUIPOS</center>");
        VerticalLayout contentEquipo = new VerticalLayout();
        FileResource resourceEquipo = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/dashboard/equipo.png"));
        Image logoEquipo = new Image("", resourceEquipo);
        logoEquipo.setWidth("130px");
        logoEquipo.setHeight("130px");
        contentEquipo.addComponents(logoEquipo, buttonEquipo);
        contentEquipo.setComponentAlignment(logoEquipo, Alignment.TOP_CENTER);
        contentEquipo.setComponentAlignment(buttonEquipo, Alignment.BOTTOM_CENTER);
        contentEquipo.setMargin(true);
        panelEquipo.setContent(contentEquipo);
        gridDashboard.addComponent(panelEquipo);

        //Jugador
        Panel panelJugador = new Panel("<center>JUGADOR</center>");
        VerticalLayout contentJugador = new VerticalLayout();
        FileResource resourceJugador = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/dashboard/jugador.png"));
        Image logoJugador = new Image("", resourceJugador);
        logoJugador.setWidth("130px");
        logoJugador.setHeight("130px");
        contentJugador.addComponents(logoJugador, buttonJugador);
        contentJugador.setComponentAlignment(logoJugador, Alignment.TOP_CENTER);
        contentJugador.setComponentAlignment(buttonJugador, Alignment.BOTTOM_CENTER);
        contentJugador.setMargin(true);
        panelJugador.setContent(contentJugador);
        gridDashboard.addComponent(panelJugador);

        //Tecnico
        Panel panelTecnico = new Panel("<center>TECNICO</center>");
        VerticalLayout contentTecnico = new VerticalLayout();
        FileResource resourceTecnico = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/dashboard/tecnico.png"));
        Image logoTecnico = new Image("", resourceTecnico);
        logoTecnico.setWidth("130px");
        logoTecnico.setHeight("130px");
        contentTecnico.addComponents(logoTecnico, buttonTecnico);
        contentTecnico.setComponentAlignment(logoTecnico, Alignment.TOP_CENTER);
        contentTecnico.setComponentAlignment(buttonTecnico, Alignment.BOTTOM_CENTER);
        contentTecnico.setMargin(true);
        panelTecnico.setContent(contentTecnico);
        gridDashboard.addComponent(panelTecnico);
        
        Panel panelAsignar = new Panel("<center>ASIGNAR PARTIDO</center>");
        VerticalLayout contentAsignar = new VerticalLayout();
        FileResource resourceAsignar = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/dashboard/asignar.jpg"));
        Image logoAsignar = new Image("", resourceAsignar);
        logoAsignar.setWidth("130px");
        logoAsignar.setHeight("130px");
        contentAsignar.setMargin(true);
        contentAsignar.addComponents(logoAsignar,buttonAsignarPartido);
        contentAsignar.setComponentAlignment(logoAsignar, Alignment.TOP_CENTER);
        contentAsignar.setComponentAlignment(buttonAsignarPartido, Alignment.BOTTOM_CENTER);
        panelAsignar.setContent(contentAsignar);
        gridDashboard.addComponent(panelAsignar);

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
        while (it1.hasNext()) {
            Calendario c = (Calendario) it1.next();
            long timestamp = c.getAnyo().getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);
            tablaCalendario.addItem(new Object[] { cal.get(Calendar.YEAR) }, i);
            i++;
        }
        tablaCalendario.setPageLength(calendarios.size());

        //Formulario
        formCalendario = new FormLayout();
        formCalendario.setSizeUndefined();
        formCalendario
                .addComponents(fieldAnyoCalendario, buttonCrearCalendario, buttonActualizarCalendario, buttonBorrarCalendario);
        formCalendario.setStyleName("formCalendario");
        formCalendario.setMargin(true);
        menuLigas.addComponents(tablaCalendario, formCalendario);
        menuLigas.setSpacing(true);
        return calendarios;
    }

    public static List<Jornada> mostrarTablaJornada(HorizontalLayout menuLigas) {
        menuLigas.removeAllComponents();
        JornadaDAO jornadadao = new JornadaDAO();
        //Tabla
        tablaJornada.removeAllItems();
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
        while (it.hasNext()) {
            Calendario c = (Calendario) it.next();
            long timestamp = c.getAnyo().getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);
            List<Jornada> listaJornadas = jornadadao.getJornadas(c.getId());
            Iterator it1 = listaJornadas.iterator();
            while (it1.hasNext()) {
                Jornada j = (Jornada) it1.next();
                returnJornadas.add(j);
                tablaJornada
                        .addItem(new Object[] { j.getNumero(), new SimpleDateFormat("dd/MM/yyyy").format(j.getFecha()),
                                cal.get(Calendar.YEAR) }, i);
                i++;
            }
            selectCalendario.addItem(c);
            selectCalendario.setItemCaption(c, String.valueOf(cal.get(Calendar.YEAR)));
            selectCalendario.setNullSelectionAllowed(false);
        }
        tablaJornada.setPageLength(i - 1);

        //Formulario
        formJornada = new FormLayout();
        formJornada.setSizeUndefined();
        formJornada
                .addComponents(fieldNumeroJornada, selectCalendario, fieldfechaJornada, buttonCrearJornada, buttonActualizarJornada);
        formJornada.setStyleName("formCalendario");
        formJornada.setMargin(true);
        menuLigas.addComponents(tablaJornada, formJornada);
        menuLigas.setSpacing(true);
        return returnJornadas;
    }
    
    public static List<Partido> mostrarTablaPartidos(HorizontalLayout menuLigas){
        menuLigas.removeAllComponents();
        PartidoDAO partidoDAO = new PartidoDAO();
        HorizontalLayout partidosCrud = new HorizontalLayout();
        partidosCrud.setSizeFull();
        partidosCrud.setMargin(true);
        partidosCrud.setSpacing(true);
        
        //Tabla
        tablaPartido.setCaptionAsHtml(true);
        tablaPartido.setCaption("<b style='color:white'>PARTIDOS CON JORNADA ASIGNADA</b>");
        tablaPartido.removeAllItems();
        tablaPartido.setWidth(100, UNITS_PERCENTAGE);
        tablaPartido.setSelectable(true);
        tablaPartido.setMultiSelect(false);
        tablaPartido.setImmediate(true);
        tablaPartido.addContainerProperty("LOCAL", String.class, null);
        tablaPartido.addContainerProperty("VISITANTE", String.class, null);
        tablaPartido.addContainerProperty("JORNADA", Integer.class, null);
        
        LigaDAO ligadao = new LigaDAO();
        Liga liga = ligadao.buscarLigaporNombre(nombreLigaSelected);
        partidos = partidoDAO.getPartidosPorLiga(liga);
        Iterator it = partidos.iterator();
        int i = 1;
        while (it.hasNext()){
            Partido p = (Partido)it.next();
            tablaPartido.addItem(new Object[]{p.getEquipoByLocalId().getNombre(), p.getEquipoByVisitanteId().getNombre(), p.getJornadaByJornadaId().getNumero()}, i);
            i++;
        }
        
        EquipoDAO equipoDAO = new EquipoDAO();
        List<Equipo> lstEquipo = equipoDAO.getEquiposIdLiga(liga.getId());
        Iterator it2 = lstEquipo.iterator();
        while (it2.hasNext()){
            Equipo e = (Equipo) it2.next();
            selectEquipoLocal.addItem(e);
            selectEquipoLocal.setItemCaption(e, e.getNombre());
            selectEquipoLocal.setNullSelectionAllowed(false);
            
            selectEquipoVisitante.addItem(e);
            selectEquipoVisitante.setItemCaption(e, e.getNombre());
            selectEquipoVisitante.setNullSelectionAllowed(false);
        }
        
        tablaPartido.setPageLength(partidos.size());
        
        //Formulario
        formPartido = new FormLayout();
        formPartido.setSizeUndefined();
        formPartido.addComponents(selectEquipoLocal, selectEquipoVisitante, buttonCrearPartido, buttonActualizarPartido, buttonBorrarPartido);
        formPartido.setStyleName("formCalendario");
        formPartido.setMargin(true);
        
        partidosCrud.addComponents(tablaPartido, tablaPartidoNull, formPartido);
        tabPartido = new TabSheet();
        tabPartido.addTab(partidosCrud, "GESTIÓN PARTIDOS");
        menuLigas.addComponent(tabPartido);
        menuLigas.setSpacing(true);
        return partidos;
    }
    
    public static List<Partido> mostrarTablaPartidosNull(HorizontalLayout menuLigas){
        menuLigas.removeAllComponents();
        PartidoDAO partidoDAO = new PartidoDAO();
        //Tabla
        tablaPartidoNull.setCaptionAsHtml(true);
        tablaPartidoNull.setCaption("<b style='color:white'>PARTIDOS SIN JORNADA ASIGNADA</b>");
        tablaPartidoNull.removeAllItems();
        tablaPartidoNull.setWidth(100, UNITS_PERCENTAGE);
        tablaPartidoNull.setSelectable(true);
        tablaPartidoNull.setMultiSelect(false);
        tablaPartidoNull.setImmediate(true);
        tablaPartidoNull.addContainerProperty("LOCAL", String.class, null);
        tablaPartidoNull.addContainerProperty("VISITANTE", String.class, null);
        tablaPartidoNull.addContainerProperty("JORNADA", String.class, null);
        
        LigaDAO ligadao = new LigaDAO();
        Liga liga = ligadao.buscarLigaporNombre(nombreLigaSelected);
        partidosNull = partidoDAO.getPartidosNullPorLiga(liga);
        Iterator it = partidosNull.iterator();
        int i = 1;
        while (it.hasNext()){
            Partido p = (Partido)it.next();
            tablaPartidoNull.addItem(new Object[]{p.getEquipoByLocalId().getNombre(), p.getEquipoByVisitanteId().getNombre(), "SIN JORNADA"}, i);
            i++;
        }
        
        tablaPartidoNull.setPageLength(partidosNull.size());
        
        return partidosNull;
    }
    
    public static void mostrarTablaEquipo(HorizontalLayout menuLigas) {
        menuLigas.removeAllComponents();
        LigaDAO ligadao = new LigaDAO();
        EquipoDAO equipodao = new EquipoDAO();
        JugadorDAO jugadordao = new JugadorDAO();
        
        //Tabla
        tablaEquipo.removeAllItems();
        tablaEquipo.setWidth(100, UNITS_PERCENTAGE);
        tablaEquipo.setSelectable(true);
        tablaEquipo.setMultiSelect(false);
        tablaEquipo.setImmediate(true);
        tablaEquipo.addContainerProperty("EQUIPO", String.class, null);
        tablaEquipo.addContainerProperty("LIGA", String.class, null);
        tablaEquipo.addContainerProperty("PUNTOS", Integer.class, null);

        Liga liga = ligadao.buscarLigaporNombre(nombreLigaSelected);
        List<Equipo> equipos = equipodao.getEquiposIdLiga(liga.getId());
        Iterator it = equipos.iterator();
        int i = 1;
        while (it.hasNext()) {
            Equipo e = (Equipo) it.next();
            tablaEquipo.addItem(new Object[] { e.getNombre(), e.getLigaByLigaId().getNombre(),e.getPuntos()}, i);
            i++;
        }
        tablaEquipo.setPageLength(i - 1);

      
        menuLigas.addComponent(tablaEquipo);
        menuLigas.setSpacing(true);
    }
    
    public static List<Jugador> mostrarTablaJugador(HorizontalLayout menuLigas) {
        menuLigas.removeAllComponents();
        LigaDAO ligadao = new LigaDAO();
        EquipoDAO equipodao = new EquipoDAO();
        JugadorDAO jugadordao = new JugadorDAO();
        
        //Tabla
        tablaJugador.removeAllItems();
        tablaJugador.setWidth(100, UNITS_PERCENTAGE);
        tablaJugador.setSelectable(true);
        tablaJugador.setMultiSelect(false);
        tablaJugador.setImmediate(true);
        tablaJugador.setSizeFull();
        tablaJugador.addContainerProperty("NOMBRE", String.class, null);
        tablaJugador.addContainerProperty("NACIONALIDAD", String.class, null);
        tablaJugador.addContainerProperty("POSICIÓN", String.class, null);
        tablaJugador.addContainerProperty("EDAD", Integer.class, null);
        tablaJugador.addContainerProperty("GOLES", Integer.class, null);
        tablaJugador.addContainerProperty("PASES", Integer.class, null);
        tablaJugador.addContainerProperty("FALTAS", Integer.class, null);
        tablaJugador.addContainerProperty("EXPULSIONES", Integer.class, null);
        tablaJugador.addContainerProperty("PARADAS", Integer.class, null);
        tablaJugador.addContainerProperty("TIROS", Integer.class, null);
        tablaJugador.addContainerProperty("EQUIPO", String.class, null);

        Liga liga = ligadao.buscarLigaporNombre(nombreLigaSelected);
        List<Equipo> equipos = equipodao.getEquiposIdLiga(liga.getId());
        Iterator it = equipos.iterator();
        List<Jugador> returnJugadores = new ArrayList<Jugador>();
        int i = 1;
        while (it.hasNext()) {
            Equipo e = (Equipo) it.next();
            List<Jugador> listaJugadores = jugadordao.getJugadoresIdEquipo(e.getId());
            Iterator it1 = listaJugadores.iterator();
            while (it1.hasNext()) {
                Jugador j = (Jugador) it1.next();
                returnJugadores.add(j);
                tablaJugador.addItem(new Object[] { j.getNombre(), j.getNacionalidad(),j.getPosicion(),j.getEdad(), j.getGoles(), j.getPases(), j.getFaltas(), j.getExpulsiones(), j.getParadas(),j.getTiros(),j.getEquipoByEquipoId().getNombre() }, i);
                i++;
            }
            selectEquipo.addItem(e);
            selectEquipo.setItemCaption(e, e.getNombre());
            selectEquipo.setNullSelectionAllowed(false);
        }
        tablaJugador.setPageLength(i - 1);

        //Formulario
        HorizontalLayout layoutFormJugador = new HorizontalLayout();
        formJugador = new FormLayout();
        formJugador.setSizeUndefined();
        formJugador
                .addComponents(fieldNombreJugador, fieldNacionalidadJugador, fieldPosicionJugador, fieldEdadJugador, fieldGolesJugador, fieldPasesJugador,fieldFaltasJugador,fieldExpulsionesJugador);
        formJugador.setStyleName("formCalendario");
        formJugador.setMargin(true);
        formJugador2 = new FormLayout();
        formJugador2.setSizeUndefined();
        formJugador2
                .addComponents(fieldParadasJugador,fieldTirosJugador,selectEquipo,buttonCrearJugador,buttonActualizarJugador,buttonBorrarJugador);
        formJugador2.setStyleName("formCalendario");
        formJugador2.setMargin(true);
        layoutFormJugador.addComponents(formJugador,formJugador2);
        menuLigas.addComponents(tablaJugador, layoutFormJugador);
        menuLigas.setSpacing(true);
        return returnJugadores;
    }
    
     public static List<Tecnico> mostrarTablaTecnico(HorizontalLayout menuLigas) {
        menuLigas.removeAllComponents();
        LigaDAO ligadao = new LigaDAO();
        EquipoDAO equipodao = new EquipoDAO();
        EquipoTecnicoDAO tecnicodao = new EquipoTecnicoDAO();
        
        //Tabla
        tablaTecnico.removeAllItems();
        tablaTecnico.setWidth(100, UNITS_PERCENTAGE);
        tablaTecnico.setSelectable(true);
        tablaTecnico.setMultiSelect(false);
        tablaTecnico.setImmediate(true);
        tablaTecnico.setSizeFull();
        tablaTecnico.addContainerProperty("NOMBRE", String.class, null);
        tablaTecnico.addContainerProperty("PUESTO", String.class, null);
        tablaTecnico.addContainerProperty("EQUIPO", String.class, null);

        Liga liga = ligadao.buscarLigaporNombre(nombreLigaSelected);
        List<Equipo> equipos = equipodao.getEquiposIdLiga(liga.getId());
        Iterator it = equipos.iterator();
        List<Tecnico> returnTecnicos = new ArrayList<Tecnico>();
        int i = 1;
        while (it.hasNext()) {
            Equipo e = (Equipo) it.next();
            List<Tecnico> listaTecnicos = tecnicodao.getEquipoTecnicoIdEquipo(e.getId());
            Iterator it1 = listaTecnicos.iterator();
            while (it1.hasNext()) {
                Tecnico t = (Tecnico) it1.next();
                returnTecnicos.add(t);
                tablaTecnico.addItem(new Object[] { t.getNombre(), t.getPuesto(),t.getEquipoByEquipoId().getNombre()}, i);
                i++;
            }
            selectEquipoTecnico.addItem(e);
            selectEquipoTecnico.setItemCaption(e, e.getNombre());
            selectEquipoTecnico.setNullSelectionAllowed(false);
        }
        tablaTecnico.setPageLength(i - 1);

        //Formulario
        formTecnico = new FormLayout();
        formTecnico.setSizeUndefined();
        formTecnico
                .addComponents(fieldNombreTecnico, fieldPuestoTecnico, selectEquipoTecnico,buttonCrearTecnico,buttonActualizarTecnico,buttonBorrarTecnico);
        formTecnico.setStyleName("formCalendario");
        formTecnico.setMargin(true);
        menuLigas.addComponents(tablaTecnico, formTecnico);
        menuLigas.setSpacing(true);
        return returnTecnicos;
    }

    public static void crearCalendario() throws ParseException {
        String anyo = fieldAnyoCalendario.getValue();
        if (!anyo.equals("")) {
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
        } else {
            Notification n = new Notification("Enter the fields", Notification.Type.ERROR_MESSAGE);
            n.setDelayMsec(1000);
            n.setPosition(Notification.POSITION_CENTERED_TOP);
            n.show(Page.getCurrent());
        }
    }

    public static void crearJornada() throws ParseException {
        Integer numero = Integer.parseInt(fieldNumeroJornada.getValue());
        Calendario c = (Calendario) selectCalendario.getValue();
        Date fecha = fieldfechaJornada.getValue();
        if (fecha == null || c == null) {
            Notification n = new Notification("Enter the fields", Notification.Type.ERROR_MESSAGE);
            n.setDelayMsec(1000);
            n.setPosition(Notification.POSITION_CENTERED_TOP);
            n.show(Page.getCurrent());
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            cal.set(Calendar.MILLISECOND, 0);
            Timestamp timestamp = new java.sql.Timestamp(fecha.getTime());
            if (!numero.equals("")) {
                LigaDAO ligadao = new LigaDAO();
                Liga liga = ligadao.buscarLigaporNombre(nombreLigaSelected);
                Jornada jornada = new Jornada();
                jornada.setNumero(numero);
                jornada.setCalendarioByCalendarioId(c);
                jornada.setFecha(timestamp);
                JornadaDAO jornadadao = new JornadaDAO();
                jornadadao.crearJornada(jornada);
            } else {
                Notification n = new Notification("Enter the fields", Notification.Type.ERROR_MESSAGE);
                n.setDelayMsec(1000);
                n.setPosition(Notification.POSITION_CENTERED_TOP);
                n.show(Page.getCurrent());
            }
        }
    }
    
    public static void crearPartido() throws ParseException{
        PartidoDAO partidoDAO = new PartidoDAO();
        Equipo eLocal = (Equipo) selectEquipoLocal.getValue();
        Equipo eVisitante = (Equipo) selectEquipoVisitante.getValue();
        if (partidoDAO.buscarPartidoIdaVuelta(eLocal, eVisitante)==null){
            Partido partido = new Partido();
            partido.setEquipoByLocalId(eLocal);
            partido.setEquipoByVisitanteId(eVisitante);
            partidoDAO.crearPartido(partido);
        }
        
    }
        
    public static void crearJugador() throws ParseException {
        String nombre = fieldNombreJugador.getValue();
        String nacionalidad = fieldNacionalidadJugador.getValue();
        String posicion = fieldPosicionJugador.getValue();
        Integer edad = Integer.parseInt(fieldEdadJugador.getValue());
        Integer goles = Integer.parseInt(fieldGolesJugador.getValue());
        Integer pases = Integer.parseInt(fieldPasesJugador.getValue());
        Integer faltas = Integer.parseInt(fieldFaltasJugador.getValue());
        Integer expulsiones = Integer.parseInt(fieldExpulsionesJugador.getValue());
        Integer paradas = Integer.parseInt(fieldParadasJugador.getValue());
        Integer tiros = Integer.parseInt(fieldTirosJugador.getValue());
        Equipo equipo = (Equipo)selectEquipo.getValue();
        if (nombre == null || nacionalidad == null || posicion == null || equipo == null) {
            Notification n = new Notification("Enter the fields", Notification.Type.ERROR_MESSAGE);
            n.setDelayMsec(1000);
            n.setPosition(Notification.POSITION_CENTERED_TOP);
            n.show(Page.getCurrent());
        } else {
            JugadorDAO jugadordao = new JugadorDAO();
            if (jugadordao.getJugadoresPorNombre(nombre)==null){
                Jugador jugador = new Jugador();
                jugador.setNombre(nombre);
                jugador.setNacionalidad(nacionalidad);
                jugador.setPosicion(posicion);
                jugador.setEdad(edad);
                jugador.setGoles(goles);
                jugador.setPases(pases);
                jugador.setFaltas(faltas);
                jugador.setExpulsiones(expulsiones);
                jugador.setParadas(paradas);
                jugador.setTiros(tiros);
                jugador.setEquipoByEquipoId(equipo);
                jugadordao.createJugador(jugador);
            }
        }
    }
    
    public static void crearTecnico() throws ParseException {
        String nombre = fieldNombreTecnico.getValue();
        String puesto = fieldPuestoTecnico.getValue();
        Equipo equipo = (Equipo)selectEquipoTecnico.getValue();
        if (nombre == null || puesto == null || equipo == null) {
            Notification n = new Notification("Enter the fields", Notification.Type.ERROR_MESSAGE);
            n.setDelayMsec(1000);
            n.setPosition(Notification.POSITION_CENTERED_TOP);
            n.show(Page.getCurrent());
        } else {
            EquipoTecnicoDAO tecnicodao = new EquipoTecnicoDAO();
            if (tecnicodao.getTecnico(nombre)==null){
                Tecnico tecnico = new Tecnico();
                tecnico.setNombre(nombre);
                tecnico.setPuesto(puesto);
                tecnico.setEquipoByEquipoId(equipo);
                tecnicodao.crearTecnico(tecnico);
            }
        }
    }

    @WebServlet(urlPatterns = "/dashboard/*", name = "DashboardServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Dashboard.class, productionMode = false)
    public static class DashboardServlet extends VaadinServlet {
    }
}
