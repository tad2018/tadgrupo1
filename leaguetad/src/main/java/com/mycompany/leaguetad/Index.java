package com.mycompany.leaguetad;

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

        // No captions for fields is they are provided in the template
//        content.addComponent(new TextField(), "username");
//        content.addComponent(new TextField(), "password");
//        content.addComponent(new Button("Login"), "okbutton");
        
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
        

        final Label liga = new Label("Ligas");
        clasificacionesLayout.addComponent(liga);
        
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
