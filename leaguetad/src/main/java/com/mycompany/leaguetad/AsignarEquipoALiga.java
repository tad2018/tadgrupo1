/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad;

import com.mycompany.leaguetad.dao.EquipoDAO;
import com.mycompany.leaguetad.persistence.Equipo;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.StreamVariable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.AcceptItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Html5File;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Asus
 */
@Theme("tests-valo-dark")
public class AsignarEquipoALiga extends UI {

    Table tablaEquiposSinLiga = new Table();
    Table tablaEquiposPorLiga = new Table();
    
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        final HorizontalLayout arriba = new HorizontalLayout();
        arriba.setSizeFull();
        final HorizontalLayout abajo = new HorizontalLayout();
        abajo.setSizeFull();

        mostrarTablaEquiposSinLiga();
        mostrarTablaEquiposPorLiga();

        arriba.addComponent(tablaEquiposSinLiga);
        arriba.setComponentAlignment(tablaEquiposSinLiga, Alignment.MIDDLE_CENTER);
        abajo.addComponent(tablaEquiposPorLiga);
        abajo.setComponentAlignment(tablaEquiposPorLiga, Alignment.MIDDLE_CENTER);
        layout.addComponents(arriba, abajo);
        setContent(layout);
    }

    private void mostrarTablaEquiposSinLiga() {
        //Tabla Equipos sin liga
        tablaEquiposSinLiga.setCaptionAsHtml(true);
        tablaEquiposSinLiga.setCaption("<b>EQUIPOS SIN LIGA ASIGNADA</b>");
        tablaEquiposSinLiga.addContainerProperty("Nombre", String.class, null);

        EquipoDAO equipoDAO = new EquipoDAO();
        List<Equipo> lstEquipos = equipoDAO.getEquiposSinLiga();
        
        for (int i = 0; i < lstEquipos.size(); i++) {
            Equipo eq = (Equipo) lstEquipos.get(i);
            tablaEquiposSinLiga.addItem(new Object[]{eq.getNombre()}, i + 1);
        }
        tablaEquiposSinLiga.setPageLength(tablaEquiposSinLiga.size());
        tablaEquiposSinLiga.setSelectable(true);
        tablaEquiposSinLiga.setImmediate(true);
        tablaEquiposSinLiga.setDragMode(Table.TableDragMode.ROW);
//        tablaEquiposSinLiga.setDropHandler(new DropHandler() {
//            @Override
//            public void drop(DragAndDropEvent event) {
//                final DataBoundTransferable t = (DataBoundTransferable)event.getTransferable();
//                Object ItemId = (Object)t.getItemId();
//                tablaEquiposPorLiga.addItem(ItemId);
//                tablaEquiposSinLiga.removeItem(ItemId);
//            }
//
//            @Override
//            public AcceptCriterion getAcceptCriterion() {
//                return AcceptItem.ALL;
//            }
//        });
    }

    private void mostrarTablaEquiposPorLiga() {
        //Tabla Equipos sin liga
        tablaEquiposPorLiga.setCaptionAsHtml(true);
        tablaEquiposPorLiga.setCaption("<b>EQUIPOS </b>");
        tablaEquiposPorLiga.addContainerProperty("Nombre", String.class, null);

        EquipoDAO equipoDAO = new EquipoDAO();
        List<Equipo> lstEquipos = equipoDAO.getEquiposIdLiga(1);
        
        for (int i = 0; i < lstEquipos.size(); i++) {
            Equipo eq = (Equipo) lstEquipos.get(i);
            tablaEquiposPorLiga.addItem(new Object[]{eq.getNombre()}, i + 1);
        }
        tablaEquiposPorLiga.setPageLength(tablaEquiposPorLiga.size());
        tablaEquiposPorLiga.setSelectable(true);
        tablaEquiposPorLiga.setImmediate(true);
        tablaEquiposPorLiga.setDragMode(Table.TableDragMode.ROW);
        tablaEquiposPorLiga.setDropHandler(new DropHandler() {
            @Override
            public void drop(DragAndDropEvent event) {
                final DataBoundTransferable t = (DataBoundTransferable)event.getTransferable();
                
                Object itemId = (Object) t.getItemId();
                equipoDAO.añadirEquipoALiga(itemId, 1);
                
//                int itemId = (int)t.getItemId();
//                Equipo e = equipoDAO.getEquipoPorId(itemId);
//                tablaEquiposPorLiga.addItem(new Object[]{e.getNombre()}, 8);
//                tablaEquiposSinLiga.removeItem(e);
            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptItem.ALL;
            }
        });
    }

    @WebServlet(urlPatterns = "/asignarequipoaliga/*", name = "AsignarEquipoALigaServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AsignarEquipoALiga.class, productionMode = false)
    public static class AsignarEquipoALigaServlet extends VaadinServlet {
    }
}
