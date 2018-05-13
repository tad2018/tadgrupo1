package com.mycompany.leaguetad;

import com.mycompany.leaguetad.dao.LigaDAO;
import com.mycompany.leaguetad.persistence.Equipo;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import java.io.File;

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
        final VerticalSplitPanel layout = new VerticalSplitPanel();
        layout.setStyleName("fondo");
        
        final HorizontalLayout logosLayout = new HorizontalLayout();
        final HorizontalLayout loginLayout = new HorizontalLayout();
        
        Panel loginPanel = new Panel("<center>LOGIN</center>");
        loginPanel.setStyleName("panelLogin");
        VerticalLayout content = new VerticalLayout();
        loginPanel.setContent(content);
        loginPanel.setWidth("400px");
        
        final Label nombreUsuarioLabel = new Label("Nombre Usuario");
        final Label passwordLabel = new Label("Contraseña");
        
        final TextField nombreUsuarioText = new TextField();
        nombreUsuarioText.setWidth("100%");
        final PasswordField passwordText = new PasswordField();
        passwordText.setWidth("100%");
        
        Button buttonIniciarSesion = new Button("Iniciar Sesión");
        buttonIniciarSesion.addClickListener( e -> {
            if (nombreUsuarioText.getValue().equals("admin") && passwordText.getValue().equals("admin")){
                WrappedSession session = getSession().getSession();
                session.setAttribute("user", nombreUsuarioText.getValue());
                getPage().setLocation("/dashboard");
            }
            else{
                Notification n = new Notification("User not exists",Notification.Type.ERROR_MESSAGE);
                n.setDelayMsec(1000);
                n.setPosition(Notification.POSITION_CENTERED_BOTTOM);
                n.show(Page.getCurrent());
            }
            
        });
        
        buttonIniciarSesion.setStyleName("buttonSuccess");

        content.addComponents(nombreUsuarioLabel, nombreUsuarioText, passwordLabel, passwordText, buttonIniciarSesion);
        content.setComponentAlignment(buttonIniciarSesion, Alignment.MIDDLE_CENTER);
        content.setSpacing(true);
        
        String basepath = VaadinService.getCurrent()
                  .getBaseDirectory().getAbsolutePath();

        // Image as a file resource
        FileResource resourceSantander = new FileResource(new File(basepath +"/VAADIN/themes/tests-valo-dark/img/liga_santander_logo.png"));

        // Show the image in the application
        Image logoSantander = new Image("", resourceSantander);
        logoSantander.setWidth("200px");
        logoSantander.setHeight("190px");
        
        FileResource resourcePremier = new FileResource(new File(basepath +"/VAADIN/themes/tests-valo-dark/img/premier_league_logo.png"));

        // Show the image in the application
        Image logoPremier = new Image("", resourcePremier);
        logoPremier.setWidth("200px");
        logoPremier.setHeight("190px");
        
        FileResource resourceCalcio = new FileResource(new File(basepath +"/VAADIN/themes/tests-valo-dark/img/calcio_logo.png"));

        // Show the image in the application
        Image logoCalcio = new Image("", resourceCalcio);
        logoCalcio.setWidth("200px");
        logoCalcio.setHeight("190px");
        
        logosLayout.addComponents(logoSantander,logoPremier,logoCalcio);
        logosLayout.setComponentAlignment(logoSantander, Alignment.TOP_CENTER);
        logosLayout.setComponentAlignment(logoPremier, Alignment.TOP_CENTER);
        logosLayout.setComponentAlignment(logoCalcio, Alignment.TOP_CENTER);
        logosLayout.setSizeFull();
        
        loginLayout.addComponents(loginPanel);
        loginLayout.setSizeFull();
        loginLayout.setComponentAlignment(loginPanel, Alignment.TOP_CENTER);
        
        layout.addComponents(logosLayout, loginLayout);
        layout.setSplitPosition(30);
        layout.setLocked(true);
        layout.setSizeFull();
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/admin/*", name = "IndexServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Index.class, productionMode = false)
    public static class IndexServlet extends VaadinServlet {
    }
}
