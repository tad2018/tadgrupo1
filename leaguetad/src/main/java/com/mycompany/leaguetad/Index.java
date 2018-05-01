package com.mycompany.leaguetad;

import com.mycompany.leaguetad.dao.LigaDAO;
import com.mycompany.leaguetad.model.Equipo;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("tests-valo-dark")
public class Index extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalSplitPanel layout = new HorizontalSplitPanel();
        layout.setStyleName("fondo");
        
        final VerticalLayout clasificacionesLayout = new VerticalLayout();
        final VerticalLayout loginLayout = new VerticalLayout();
        
        Panel loginPanel = new Panel("Login");
        loginPanel.setStyleName("panelLogin");
        loginPanel.setWidth(150, Unit.PIXELS);
        loginPanel.setHeight(150, Unit.PIXELS);
        VerticalLayout content = new VerticalLayout();
        loginPanel.setContent(content);
        loginPanel.setSizeUndefined();
        
        final Label nombreUsuarioLabel = new Label("Nombre Usuario");
        final Label passwordLabel = new Label("Contraseña");
        
        final TextField nombreUsuarioText = new TextField();
        final PasswordField passwordText = new PasswordField();
        
        Button buttonIniciarSesion = new Button("Iniciar Sesión");
        buttonIniciarSesion.addClickListener( e -> {
            layout.addComponent(new Label("Hey"));
        });
        
        buttonIniciarSesion.setStyleName("buttonSuccess");

        content.addComponents(nombreUsuarioLabel, nombreUsuarioText, passwordLabel, passwordText, buttonIniciarSesion);
        content.setSpacing(true);
        loginLayout.addComponents(loginPanel);
        
        final Table tablaLigaEspañola = new Table("Liga Santander");
        //tabla.setSizeFull();
        tablaLigaEspañola.setWidth(100, UNITS_PERCENTAGE);
        tablaLigaEspañola.setHeight(50, UNITS_PERCENTAGE);
        tablaLigaEspañola.setSelectable(true);
        tablaLigaEspañola.setMultiSelect(true);
        tablaLigaEspañola.setImmediate(true);
        tablaLigaEspañola.addContainerProperty("", Integer.class, null);
        tablaLigaEspañola.addContainerProperty("EQUIPOS", String.class, null);
        tablaLigaEspañola.addContainerProperty("PUNTOS", Integer.class, null);
        
        LigaDAO ligadao = new LigaDAO();
        final Equipo[] listadoEquipos = ligadao.getClasificacionLigaEspanola();
        for(int i=0; i < listadoEquipos.length; i++){
            Equipo e = (Equipo) listadoEquipos[i];
            tablaLigaEspañola.addItem(new Object[]{i+1, e.getNombre(), e.getPuntos()}, i+1);
            i++;
        }
        
        clasificacionesLayout.addComponent(tablaLigaEspañola);
        
        layout.addComponents(clasificacionesLayout, loginLayout);
        layout.setSplitPosition(70, Unit.PERCENTAGE);
        layout.setLocked (true);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "IndexServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Index.class, productionMode = false)
    public static class IndexServlet extends VaadinServlet {
    }
}
