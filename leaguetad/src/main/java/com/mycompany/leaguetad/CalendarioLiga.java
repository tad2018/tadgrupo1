/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetad;

import com.mycompany.leaguetad.dao.CalendarioDAO;
import com.mycompany.leaguetad.dao.JornadaDAO;
import com.mycompany.leaguetad.dao.PartidoDAO;
import com.mycompany.leaguetad.persistence.Calendario;
import com.mycompany.leaguetad.persistence.Jornada;
import com.mycompany.leaguetad.persistence.Partido;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.calendar.DateConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.TableTransferable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickEvent;
import com.vaadin.ui.components.calendar.CalendarTargetDetails;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;
import com.vaadin.ui.components.calendar.handler.BasicDateClickHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.annotation.WebServlet;

/**
 * @author expositod
 */
@Theme("tests-valo-dark")
public class CalendarioLiga extends UI {

    CalendarioDAO calendarioDAO = new CalendarioDAO();
    Calendario c = calendarioDAO.getCalendario(1, 2018);
    JornadaDAO jornadaDAO = new JornadaDAO();
    PartidoDAO partidoDAO = new PartidoDAO();
    List<Partido> psj = partidoDAO.getPartidosSinJornada();
    List<Partido> pp = partidoDAO.getPartidos();

    Table tablaPartidos = new Table();
    Calendar calendar = new Calendar();

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("fondo");
        layout.setMargin(true);
        layout.setSpacing(true);

        final HorizontalLayout arriba = new HorizontalLayout();
        arriba.setSizeFull();
        final HorizontalLayout abajo = new HorizontalLayout();
        abajo.setSizeFull();

        mostrarTablaPartidos();
        mostrarCalendario();

        arriba.addComponent(tablaPartidos);
        arriba.setComponentAlignment(tablaPartidos, Alignment.MIDDLE_CENTER);
        abajo.addComponent(calendar);
        abajo.setComponentAlignment(calendar, Alignment.MIDDLE_CENTER);
        layout.addComponents(arriba, abajo);
        setContent(layout);
    }

    private void mostrarTablaPartidos() {
        //Tabla Partidos
        tablaPartidos.setCaptionAsHtml(true);
        tablaPartidos.setCaption("<b style='color:white'>PARTIDOS SIN DÍA Y HORA ASIGNADOS</b>");
        tablaPartidos.addContainerProperty("Liga", String.class, null);
        tablaPartidos.addContainerProperty("Local", String.class, null);
        tablaPartidos.addContainerProperty("Visitante", String.class, null);

        for (int i = 0; i < psj.size(); i++) {
            Partido par = (Partido) psj.get(i);
            tablaPartidos.addItem(new Object[] { partidoDAO.obtenerLigaPartido(par.getEquipoByLocalId()),
                    par.getEquipoByLocalId().getNombre(), par.getEquipoByVisitanteId().getNombre() }, par.getId());
        }
        tablaPartidos.setPageLength(tablaPartidos.size());
        tablaPartidos.setSelectable(true);
        tablaPartidos.setDragMode(Table.TableDragMode.ROW);
    }

    private void mostrarCalendario() {
        // Create the calendar
        calendar.setCaptionAsHtml(true);
        calendar.setCaption("<b style='color:white'>PARTIDOS POR JORNADA</b>");
        calendar.setWidth("800px");  // Undefined by default
        calendar.setHeight("500px"); // Undefined by default

        calendar.setLocale(new Locale("es", "ES"));

        // Set start date to first date in this month
        GregorianCalendar calStart = new GregorianCalendar();
        calStart.set(java.util.Calendar.DATE, 1);
        calendar.setStartDate(calStart.getTime());

        // Set end date to last day of this month
        GregorianCalendar calEnd = new GregorianCalendar();
        calEnd.set(java.util.Calendar.DATE, 1);
        calEnd.roll(java.util.Calendar.DATE, -1);
        calendar.setEndDate(calEnd.getTime());

        // Add an all-day event
        GregorianCalendar today = new GregorianCalendar();
        Iterator it = pp.iterator();
        BasicEvent dayEvent = null;
        while (it.hasNext()) {
            Partido pp = (Partido) it.next();
            dayEvent = new BasicEvent(
                    pp.getEquipoByLocalId().getNombre() + " - " + pp.getEquipoByVisitanteId().getNombre(),
                    partidoDAO.obtenerLigaPartido(pp.getEquipoByLocalId()) + ", Jornada " + partidoDAO
                            .obtenerJornadaPartido(pp.getId()) + ": " + pp.getEquipoByLocalId().getNombre() + " - " + pp
                            .getEquipoByVisitanteId().getNombre(),
                    partidoDAO.obtenerFechaPartido(pp.getId()));
            dayEvent.setAllDay(true);
            calendar.addEvent(dayEvent);
        }

        calendar.setDropHandler(new DropHandler() {
            public void drop(DragAndDropEvent event) {
                CalendarTargetDetails details
                        = (CalendarTargetDetails) event.getTargetDetails();

                TableTransferable transferable
                        = (TableTransferable) event.getTransferable();

                createEvent(details, transferable);
                removeTableRow(transferable);
            }

            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }

            private void createEvent(CalendarTargetDetails details, TableTransferable transferable) {
                Date dropTime = details.getDropTime();
                java.util.Calendar timeCalendar = details.getTargetCalendar()
                        .getInternalCalendar();
                timeCalendar.setTime(dropTime);
                timeCalendar.add(java.util.Calendar.MINUTE, 120);
                Date endTime = timeCalendar.getTime();

                Item draggedItem = transferable.getSourceComponent().
                        getItem(transferable.getItemId());

                String eventType = (String) draggedItem.getItemProperty("Local").getValue() + "-" + (String) draggedItem
                        .getItemProperty("Visitante").getValue();

                String eventDescription = (String) draggedItem.getItemProperty("Local").getValue() + "-"
                        + (String) draggedItem.getItemProperty("Visitante").getValue();

                BasicEvent newEvent = new BasicEvent();
                newEvent.setAllDay(!details.hasDropTime());
                newEvent.setCaption(eventType);
                newEvent.setDescription(eventDescription);
                newEvent.setStart(dropTime);
                newEvent.setEnd(endTime);

                Date fecha = dropTime;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fechaFormat = sdf.format(fecha);

                int idPartido = (int) transferable.getItemId();

                Jornada jornada = jornadaDAO.obtenerJornadaPorFecha(fechaFormat);
                partidoDAO.updateJornadaAPartido(jornada, idPartido);

                BasicEventProvider ep = (BasicEventProvider) details
                        .getTargetCalendar().getEventProvider();
                ep.addEvent(newEvent);
            }

            private void removeTableRow(TableTransferable transferable) {
                tablaPartidos.removeItem(transferable.getItemId());
            }

        });

        //        //Handle clicks on dates
        calendar.setHandler(new BasicDateClickHandler() {
            public void dateClick(DateClickEvent event) {
                Calendar cal = event.getComponent();

                // Check if the current range is already one day long
                long currentCalDateRange = cal.getEndDate().getTime()
                        - cal.getStartDate().getTime();

                // From one-day view, zoom out to week view
                if (currentCalDateRange <= DateConstants.DAYINMILLIS) {
                    // Change the date range to the current week
                    GregorianCalendar weekstart = new GregorianCalendar();
                    GregorianCalendar weekend = new GregorianCalendar();
                    weekstart.setTime(event.getDate());
                    weekend.setTime(event.getDate());
                    weekstart.setFirstDayOfWeek(java.util.Calendar.SUNDAY);
                    weekstart.set(java.util.Calendar.HOUR_OF_DAY, 0);
                    weekstart.set(java.util.Calendar.DAY_OF_WEEK,
                            java.util.Calendar.SUNDAY);
                    weekend.set(java.util.Calendar.HOUR_OF_DAY, 23);
                    weekend.set(java.util.Calendar.DAY_OF_WEEK,
                            java.util.Calendar.SATURDAY);
                    cal.setStartDate(weekstart.getTime());
                    cal.setEndDate(weekend.getTime());

                    Notification.show("Custom zoom to week");
                } else {
                    // Default behavior, change date range to one day
                    super.dateClick(event);
                }
            }
        });
    }

    @WebServlet(urlPatterns = "/calendarioLiga/*", name = "CalendarioLigaServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CalendarioLiga.class, productionMode = false)
    public static class CalendarioLigaServlet extends VaadinServlet {
    }
}
