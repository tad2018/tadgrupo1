/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author expositod
 */
@Theme("tests-valo-dark")
public class Dashboard extends UI{

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        Label label = new Label("Hola");
        layout.addComponent(label);
        setContent(layout);
    }
    
    @WebServlet(urlPatterns = "/dashboard", name = "DashboardServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Dashboard.class, productionMode = false)
    public static class DashboardServlet extends VaadinServlet {
    }
}
