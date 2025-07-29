/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaServices;

import CapaModelo.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class CalendarService {
    private EventServices eventService;
    
    public CalendarService() {
        this.eventService = new EventServices();
    }
    
    /**
     * Obtener eventos agrupados por fecha
     * @return Map con fechas como clave y lista de eventos como valor
     */
    public Map<LocalDate, List<Event>> getEventsByDate() {
        List<Event> allEvents = eventService.getAllEvents();
        Map<LocalDate, List<Event>> eventsByDate = new HashMap<>();
        
        for (Event event : allEvents) {
            LocalDate date = event.getFechaHora().toLocalDate();
            eventsByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(event);
        }
        
        return eventsByDate;
    }
    
    /**
     * Obtener eventos de un mes específico
     * @param year año
     * @param month mes (1-12)
     * @return lista de eventos del mes
     */
    public List<Event> getEventsForMonth(int year, int month) {
        List<Event> allEvents = eventService.getAllEvents();
        List<Event> monthEvents = new ArrayList<>();
        
        for (Event event : allEvents) {
            LocalDateTime eventDate = event.getFechaHora();
            if (eventDate.getYear() == year && eventDate.getMonthValue() == month) {
                monthEvents.add(event);
            }
        }
        
        return monthEvents;
    }
    
    /**
     * Verificar si hay conflictos de horario
     * @param newEvent evento a verificar
     * @return boolean true si hay conflicto
     */
    public boolean hasTimeConflict(Event newEvent) {
        List<Event> allEvents = eventService.getAllEvents();
        LocalDateTime newStart = newEvent.getFechaHora();
        LocalDateTime newEnd = newStart.plusHours(1); // Asumiendo 1 hora de duración
        
        for (Event existingEvent : allEvents) {
            if (existingEvent.getId() == newEvent.getId()) continue; // Excluir el mismo evento
            
            LocalDateTime existingStart = existingEvent.getFechaHora();
            LocalDateTime existingEnd = existingStart.plusHours(1);
            
            // Verificar solapamiento
            if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                return true;
            }
        }
        
        return false;
    }
}
