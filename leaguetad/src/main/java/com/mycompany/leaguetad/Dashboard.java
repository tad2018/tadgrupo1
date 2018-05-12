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
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author expositod
 */
@Theme("tests-valo-dark")
public class Dashboard extends UI{

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
        for(int i=0; i < listadoLigas.length; i++){
            Liga l = (Liga) listadoLigas[i];
            ligas.addItem(l.getNombre(), null, null);
            panel = new Panel("<center>"+l.getNombre()+"</center>");
            panel.setWidth("400px");
            gridLigas.addComponent(panel);
        }

        
        menuHorizontal.addComponents(selection,menu);
        menuLigas.addComponent(gridLigas);
        menuLigas.setSizeFull();
        menuLigas.setComponentAlignment(gridLigas, Alignment.MIDDLE_CENTER);
        layout.addComponents(menuHorizontal,menuLigas);
        layout.setSplitPosition(10);
        layout.setLocked(true);
        layout.setSizeFull();
        setContent(layout);
    }
    
    @WebServlet(urlPatterns = "/dashboard/*", name = "DashboardServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Dashboard.class, productionMode = false)
    public static class DashboardServlet extends VaadinServlet {
    }
}
