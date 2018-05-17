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
import com.mycompany.leaguetad.model.CalendarioFrontend;
import com.mycompany.leaguetad.persistence.Calendario;
import com.mycompany.leaguetad.persistence.Equipo;
import com.mycompany.leaguetad.persistence.Jornada;
import com.mycompany.leaguetad.persistence.Jugador;
import com.mycompany.leaguetad.persistence.Liga;
import com.mycompany.leaguetad.persistence.Partido;
import com.mycompany.leaguetad.persistence.Tecnico;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Options3d;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.themes.GridTheme;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.servlet.annotation.WebServlet;

/**
 * @author expositod
 */
@Theme("tests-valo-dark")
@Widgetset("com.mycompany.leaguetad.MyAppWidgetset")
public class Main extends UI {

    @Override
    protected void init(VaadinRequest request) {
        HorizontalSplitPanel layout = new HorizontalSplitPanel();
        layout.setSizeFull();
        LigaDAO ligadao = new LigaDAO();
        EquipoDAO equipodao = new EquipoDAO();

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resourceSantander = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/img/logo.png"));
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
        for (int i = 0; i < ligas.length; i++) {
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
            while (it.hasNext()) {
                Equipo e = (Equipo) it.next();
                tree.addItem(e.getNombre());
                tree.setParent(e.getNombre(), nombreLiga);
                tree.setChildrenAllowed(e.getNombre(), false);
            }
        }

        String calendario = new String("Calendario");
        tree.addItem(calendario);
        tree.setChildrenAllowed(calendario, false);

        final Button buscarCalendario = new Button("Buscar Calendario");
        final ComboBox select = new ComboBox("AÑO");
        final ComboBox selectLigas = new ComboBox("LIGAS");
        tree.addItemClickListener(e -> {
            String itemSelected = e.getItemId().toString();
            if (itemSelected.equals("Clasificación")) {
                mostrarClasificacion(verticalLayout);
            } else if (ligadao.buscarLigaporPais(itemSelected) != null) {
                mostrarClasificacionPais(verticalLayout, itemSelected);
            } else if (ligadao.buscarLigaporNombre(itemSelected) != null) {
                mostrarEquiposPais(verticalLayout, itemSelected);
            } else if (equipodao.buscarIdEquipoNombre(itemSelected) != null) {
                mostrarJugadoresEquipo(verticalLayout, itemSelected);
            } else if (itemSelected.equals("Calendario")) {
                verticalLayout.setMargin(true);
                FormLayout form = new FormLayout();
                form.setStyleName("formCalendario");
                form.setSizeUndefined();
                form.setMargin(true);
                form.setSpacing(true);

                /* Select año */
                List<CalendarioFrontend> calendarios = new ArrayList<>();
                CalendarioFrontend anyo1 = new CalendarioFrontend("2017");
                CalendarioFrontend anyo2 = new CalendarioFrontend("2018");
                CalendarioFrontend anyo3 = new CalendarioFrontend("2019");

                select.removeAllItems();
                select.addItem(anyo1);
                select.addItem(anyo2);
                select.addItem(anyo3);

                select.setItemCaption(anyo1, anyo1.getAnyo());
                select.setItemCaption(anyo2, anyo2.getAnyo());
                select.setItemCaption(anyo3, anyo3.getAnyo());

                select.setNullSelectionAllowed(false);

                /* Select Liga */
                List<Liga> listaLigas = ligadao.getLigasLista();
                Iterator it = listaLigas.iterator();
                while (it.hasNext()) {
                    Liga l = (Liga) it.next();
                    selectLigas.addItem(l);
                    selectLigas.setItemCaption(l, l.getNombre());
                }

                selectLigas.setNullSelectionAllowed(false);

                form.addComponents(select, selectLigas, buscarCalendario);
                verticalLayout.removeAllComponents();
                verticalLayout.addComponent(form);
            }
        });

        buscarCalendario.addClickListener(e -> {
            CalendarioFrontend anyo = (CalendarioFrontend) select.getValue();
            Liga liga = (Liga) selectLigas.getValue();
            if (anyo == null || liga == null) {
                Notification n = new Notification("Enter the fields", Notification.Type.ERROR_MESSAGE);
                n.setDelayMsec(1000);
                n.setPosition(Notification.POSITION_CENTERED_TOP);
                n.show(Page.getCurrent());
            } else {
                mostrarJornadas(verticalLayout, anyo.getAnyo(), liga);
            }
        });

        verticalTree.addComponents(logo, tree);
        verticalTree.setComponentAlignment(logo, Alignment.TOP_CENTER);
        layout.addComponents(verticalTree, verticalLayout);
        layout.setSplitPosition(13);
        layout.setLocked(true);
        setContent(layout);
    }

    public static void mostrarClasificacion(VerticalLayout verticalLayout) {
        TabSheet sample = new TabSheet();
        sample.setSizeFull();
        final VerticalLayout layoutSantander = new VerticalLayout();
        layoutSantander.setSizeFull();
        layoutSantander.setMargin(true);
        sample.addTab(layoutSantander, "LIGA SANTANDER");

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
        for (int i = 0; i < listadoEquipos.length; i++) {
            Equipo e = (Equipo) listadoEquipos[i];
            tablaLigaEspañola.addItem(new Object[] { i + 1, e.getNombre(), e.getPuntos() }, i + 1);
        }
        tablaLigaEspañola.setPageLength(listadoEquipos.length);
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        // Image as a file resource
        FileResource resourceSantander = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/img/liga_santander_logo.png"));

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
        for (int i = 0; i < listadoEquiposInglesa.length; i++) {
            Equipo e = (Equipo) listadoEquiposInglesa[i];
            tablaLigaInglesa.addItem(new Object[] { i + 1, e.getNombre(), e.getPuntos() }, i + 1);
        }
        tablaLigaInglesa.setPageLength(listadoEquipos.length);
        FileResource resourcePremier = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/img/premier_league_logo.png"));

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
        for (int i = 0; i < listadoEquiposItaliana.length; i++) {
            Equipo e = (Equipo) listadoEquiposItaliana[i];
            tablaLigaItaliana.addItem(new Object[] { i + 1, e.getNombre(), e.getPuntos() }, i + 1);
        }
        tablaLigaItaliana.setPageLength(listadoEquipos.length);
        FileResource resourceCalcio = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/img/calcio_logo.png"));

        // Show the image in the application
        Image logoCalcio = new Image("", resourceCalcio);
        logoCalcio.setWidth("200px");
        logoCalcio.setHeight("190px");
        layoutCalcio.addComponents(logoCalcio, tablaLigaItaliana);
        layoutCalcio.setComponentAlignment(logoCalcio, Alignment.TOP_CENTER);
        verticalLayout.removeAllComponents();
        verticalLayout.addComponents(sample);
    }

    public static void mostrarClasificacionPais(VerticalLayout verticalLayout, String pais) {
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
        int i = 1;
        while (it.hasNext()) {
            Equipo e = (Equipo) it.next();
            tabla.addItem(new Object[] { i, e.getNombre(), e.getPuntos() }, i);
            i++;
        }
        tabla.setPageLength(listadoEquipos.size());

        Integer idPais = ligadao.buscarIdLigaPais(pais);
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(
                basepath + "/VAADIN/themes/tests-valo-dark/ligas/" + idPais + ".png"));
        Image logo = new Image("", resource);
        logo.setWidth("200px");
        logo.setHeight("190px");
        verticalLayout.removeAllComponents();
        verticalLayout.addComponents(logo, tabla);
        verticalLayout.setComponentAlignment(logo, Alignment.TOP_CENTER);
    }

    public static void mostrarEquiposPais(VerticalLayout verticalLayout, String nombre) {
        verticalLayout.setMargin(true);
        LigaDAO ligadao = new LigaDAO();
        Integer idLiga = ligadao.buscarIdLigaNombre(nombre);
        EquipoDAO equipodao = new EquipoDAO();
        List<Equipo> equipos = equipodao.getEquiposIdLiga(idLiga);
        int filas = (int) Math.ceil((equipos.size() / 4));
        if (filas == 0) {
            filas = 1;
        }
        GridLayout gridEquipos = new GridLayout(4, filas);
        gridEquipos.setSizeFull();
        gridEquipos.setSpacing(true);
        Panel panel;

        Iterator it = equipos.iterator();
        while (it.hasNext()) {
            Equipo e = (Equipo) it.next();
            panel = new Panel("<center>" + e.getNombre() + "</center>");
            VerticalLayout content = new VerticalLayout();
            String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            FileResource resource = new FileResource(new File(
                    basepath + "/VAADIN/themes/tests-valo-dark/equipos/" + e.getId() + ".png"));
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

    public static void mostrarJugadoresEquipo(VerticalLayout verticalLayout, String nombre) {
        verticalLayout.setMargin(true);
        TabSheet sample = new TabSheet();

        /* JUGADORES */
        final VerticalLayout layoutJugadores = new VerticalLayout();
        layoutJugadores.setSizeFull();
        layoutJugadores.setMargin(true);
        sample.addTab(layoutJugadores, "JUGADORES");

        EquipoDAO equipodao = new EquipoDAO();
        Integer idEquipo = equipodao.buscarIdEquipoNombre(nombre);
        JugadorDAO jugadordao = new JugadorDAO();
        List<Jugador> jugadores = jugadordao.getJugadoresIdEquipo(idEquipo);
        int filas = (int) Math.ceil((jugadores.size() / 4));
        if (filas == 0) {
            filas = 1;
        }
        GridLayout gridJugadores = new GridLayout(4, filas);
        gridJugadores.setSizeFull();
        gridJugadores.setSpacing(true);
        Panel panel;

        Iterator it = jugadores.iterator();
        while (it.hasNext()) {
            Jugador j = (Jugador) it.next();
            panel = new Panel("<center>" + j.getNombre() + "</center>");
            VerticalLayout content = new VerticalLayout();
            String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            FileResource resource = new FileResource(new File(
                    basepath + "/VAADIN/themes/tests-valo-dark/jugadores/" + j.getId() + ".jpg"));
            Image logo = new Image("", resource);
            logo.setWidth("130px");
            logo.setHeight("190px");
            Label posicion = new Label("POSICIÓN: " + j.getPosicion());
            Label nacionalidad = new Label("NACIONALIDAD: " + j.getNacionalidad());
            Label edad = new Label("EDAD: " + j.getEdad());
            content.addComponents(logo, posicion, edad, nacionalidad);
            content.setComponentAlignment(logo, Alignment.TOP_CENTER);
            content.setMargin(true);
            panel.setContent(content);
            gridJugadores.addComponents(panel);
        }
        layoutJugadores.addComponent(gridJugadores);

        /* CUERPO TECNICO */
        final VerticalLayout layoutCuerpoTecnico = new VerticalLayout();
        layoutCuerpoTecnico.setSizeFull();
        layoutCuerpoTecnico.setMargin(true);
        sample.addTab(layoutCuerpoTecnico, "CUERPO TÉCNICO");

        EquipoTecnicoDAO tecnicodao = new EquipoTecnicoDAO();
        List<Tecnico> tecnicos = tecnicodao.getEquipoTecnicoIdEquipo(idEquipo);
        filas = (int) Math.ceil((tecnicos.size() / 4));
        if (filas == 0) {
            filas = 1;
        }
        GridLayout gridTecnicos = new GridLayout(4, filas);
        gridTecnicos.setSizeFull();
        gridTecnicos.setSpacing(true);
        Panel panelTecnicos;

        Iterator it1 = tecnicos.iterator();
        while (it1.hasNext()) {
            Tecnico t = (Tecnico) it1.next();
            panelTecnicos = new Panel("<center>" + t.getNombre() + "</center>");
            VerticalLayout content = new VerticalLayout();
            String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            FileResource resource = new FileResource(new File(
                    basepath + "/VAADIN/themes/tests-valo-dark/tecnicos/" + t.getId() + ".jpg"));
            Image logo = new Image("", resource);
            logo.setWidth("130px");
            logo.setHeight("190px");
            Label puesto = new Label("PUESTO: " + t.getPuesto());
            content.addComponents(logo, puesto);
            content.setComponentAlignment(logo, Alignment.TOP_CENTER);
            content.setMargin(true);
            panelTecnicos.setContent(content);
            gridTecnicos.addComponents(panelTecnicos);
        }
        layoutCuerpoTecnico.addComponent(gridTecnicos);
        sample.addTab(layoutCuerpoTecnico, "CUERPO TÉCNICO");
        
        /* ESTADISTICA */
        final VerticalLayout layoutEstadistica = new VerticalLayout();
        TabSheet sampleEstadistica = new TabSheet();
        layoutEstadistica.setSizeFull();
        layoutEstadistica.setMargin(true);
        sample.addTab(layoutEstadistica, "ESTADÍSTICA EQUIPO");
        layoutEstadistica.setMargin(true);
        final VerticalLayout layoutEstadisticaGoles = new VerticalLayout();
        final VerticalLayout layoutEstadisticaPases = new VerticalLayout();
        final VerticalLayout layoutEstadisticaTiros = new VerticalLayout();

        /* GOLES */
        Chart chartGoles = new Chart(ChartType.PIE);
        Configuration confGoles = chartGoles.getConfiguration();
        ChartOptions.get().setTheme(new GridTheme());
        PlotOptionsPie optionsGoles = new PlotOptionsPie();
        optionsGoles.setDepth(45);
        optionsGoles.setInnerSize("80");//0
        optionsGoles.setSize("75%");//75
        optionsGoles.setCenter("50%", "50%");//50 50
        confGoles.setPlotOptions(optionsGoles);
        confGoles.setTitle("GOLES DEL EQUIPO");

        DataSeries seriesGoles = new DataSeries();
        seriesGoles.setName("GOLES");

        /* PASES */
        Chart chartPases = new Chart(ChartType.PIE);
        Configuration confPases = chartPases.getConfiguration();
        ChartOptions.get().setTheme(new GridTheme());
        PlotOptionsPie optionsPases = new PlotOptionsPie();
        optionsPases.setDepth(45);
        optionsPases.setInnerSize("80");//0
        optionsPases.setSize("75%");//75
        optionsPases.setCenter("50%", "50%");//50 50
        confPases.setPlotOptions(optionsPases);
        confPases.setTitle("PASES DEL EQUIPO");

        DataSeries seriesPases = new DataSeries();
        seriesPases.setName("PASES");
        //        Iterator it2= jugadores.iterator();
        //        while(it2.hasNext()){
        //            Jugador j = (Jugador)it2.next();
        //            series.add(new DataSeriesItem(j.getNombre(), j.getGoles()));
        //            seriesPases.add(new DataSeriesItem(j.getNombre(), j.getPases()));
        //        }

        //TIROS
        Chart chartTiros = new Chart(ChartType.COLUMN);
        Configuration confTiros = chartTiros.getConfiguration();
        ChartOptions.get().setTheme(new GridTheme());
        PlotOptionsColumn optionsTiros = new PlotOptionsColumn();
        confTiros.setPlotOptions(optionsPases);
        confTiros.setTitle("TIROS DEL EQUIPO");
        confTiros.getLegend().setEnabled(false);

        XAxis x = new XAxis();
        x.setType(AxisType.CATEGORY);
        confTiros.addxAxis(x);

        YAxis y = new YAxis();
        y.setTitle("Goles");
        confTiros.addyAxis(y);

        DataSeries seriesTiros = new DataSeries();
        PlotOptionsColumn plotOptionsColumnTiros = new PlotOptionsColumn();
        plotOptionsColumnTiros.setColorByPoint(true);
        seriesTiros.setPlotOptions(plotOptionsColumnTiros);

        DataSeriesItem seriesItemTiros;
        DataSeries drillSeriesTiros;

        Iterator it2 = jugadores.iterator();
        while (it2.hasNext()) {
            Jugador j = (Jugador) it2.next();
            //Items goles
            seriesGoles.add(new DataSeriesItem(j.getNombre(), j.getGoles()));
            //Items pases
            seriesPases.add(new DataSeriesItem(j.getNombre(), j.getPases()));
            //Items tiros
            seriesItemTiros = new DataSeriesItem(j.getNombre(), j.getTiros());
            seriesTiros.addItemWithDrilldown(seriesItemTiros);
        }

        Options3d options3d = new Options3d();
        options3d.setEnabled(true);
        options3d.setAlpha(60);
        confPases.getChart().setOptions3d(options3d);
        confGoles.getChart().setOptions3d(options3d);

        confGoles.addSeries(seriesGoles);
        confPases.addSeries(seriesPases);
        confTiros.addSeries(seriesTiros);
        
        layoutEstadisticaGoles.addComponent(chartGoles);
        layoutEstadisticaTiros.addComponent(chartTiros);
        layoutEstadisticaPases.addComponent(chartPases);
        
        sampleEstadistica.addTab(layoutEstadisticaGoles, "GOLES");
        sampleEstadistica.addTab(layoutEstadisticaTiros, "TIROS");
        sampleEstadistica.addTab(layoutEstadisticaPases, "PASES");
        
        layoutEstadistica.addComponents(sampleEstadistica);
        verticalLayout.removeAllComponents();
        verticalLayout.addComponent(sample);

    }

    public static void mostrarJornadas(VerticalLayout verticalLayout, String anyo, Liga liga) {
        verticalLayout.setMargin(true);
        CalendarioDAO calendariodao = new CalendarioDAO();
        JornadaDAO jornadadao = new JornadaDAO();
        PartidoDAO partidodao = new PartidoDAO();
        Calendario calendario = calendariodao.getCalendarioIndex(Integer.parseInt(anyo), liga.getId());
        if (calendario != null) {
            List<Jornada> listaJornadas = jornadadao.getJornadas(calendario.getId());
            Iterator it = listaJornadas.iterator();
            int filas = (int) Math.ceil((listaJornadas.size() / 4));
            if (filas == 0) {
                filas = 1;
            }
            GridLayout gridJornadas = new GridLayout(4, filas);
            gridJornadas.setSizeFull();
            gridJornadas.setSpacing(true);
            Panel panel;
            while (it.hasNext()) {
                Jornada j = (Jornada) it.next();
                long timestamp = j.getFecha().getTime();
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(timestamp);
                System.out.println("Año: " + cal.get(Calendar.YEAR));
                panel = new Panel(
                        "<center>JORNADA " + j.getNumero() + " - " + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal
                                .get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR) + "</center>");
                VerticalLayout content = new VerticalLayout();
                List<Partido> partidos = partidodao.getPartidos(j.getId());
                Iterator it1 = partidos.iterator();
                while (it1.hasNext()) {
                    Partido p = (Partido) it1.next();
                    Label partido = new Label(
                            p.getEquipoByLocalId().getNombre() + " - " + p.getEquipoByVisitanteId().getNombre());
                    content.setSizeFull();
                    content.addComponent(partido);
                    content.setComponentAlignment(partido, Alignment.MIDDLE_CENTER);
                    content.setMargin(true);
                }
                panel.setContent(content);

                gridJornadas.addComponent(panel);
            }
            verticalLayout.removeAllComponents();
            verticalLayout.addComponent(gridJornadas);
        } else {
            Notification n = new Notification("Calendar not exists", Notification.Type.ERROR_MESSAGE);
            n.setDelayMsec(1000);
            n.setPosition(Notification.POSITION_CENTERED_TOP);
            n.show(Page.getCurrent());
        }
    }

    @WebServlet(urlPatterns = "/*", name = "MainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MainServlet extends VaadinServlet {
    }
}
