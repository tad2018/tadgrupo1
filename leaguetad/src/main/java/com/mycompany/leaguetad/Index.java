package com.mycompany.leaguetad;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
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
@Theme("mytheme")
public class Index extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalSplitPanel layout = new HorizontalSplitPanel();
        
        final VerticalLayout clasificacionesLayout = new VerticalLayout();
        final VerticalLayout loginLayout = new VerticalLayout();
        
        final Label iniciarSesionLabel = new Label("Iniciar Sesión en League TAD");
        final Label nombreUsuarioLabel = new Label("Nombre Usuario");
        final Label passwordLabel = new Label("Contraseña");
        
        final TextField nombreUsuarioText = new TextField();
        final PasswordField passwordText = new PasswordField();
        
        Button button = new Button("Iniciar Sesión");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Hey"));
        });

        loginLayout.addComponents(iniciarSesionLabel, nombreUsuarioLabel, nombreUsuarioText, passwordLabel, passwordText);
        

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
