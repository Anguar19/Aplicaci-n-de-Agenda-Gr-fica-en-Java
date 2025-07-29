/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaServices;

import CapaDao.EventDB;
import CapaModelo.Event;
import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class EventServices {
    private EventDB eventDB;
    
    public EventServices() {
        this.eventDB = new EventDB();
    }
    
    /**
     * Validar y crear un nuevo evento
     * @param event evento a crear
     * @return boolean resultado de la operaciónes 
     * 
     * 
     */
    public boolean createEvent(Event event) {
        // Validaciones de negocio
        if (event.getDescripcion() == null || event.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (event.getFechaHora() == null) {
            throw new IllegalArgumentException("La fecha y hora son obligatorias");
        }
        if (event.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se pueden crear eventos en el pasado");
        }
        
        return eventDB.create(event);
    }
    
    /**
     * Obtener todos los eventos
     * @return lista de eventos
     */
    public List<Event> getAllEvents() {
        return eventDB.readAll();
    }
    
    /**
     * Obtener eventos próximos
     * @return lista de eventos próximos
     */
    public List<Event> getUpcomingEvents() {
        return eventDB.getUpcomingEvents();
    }
    
    /**
     * Actualizar un evento existente
     * @param event evento con datos actualizados
     * @return boolean resultado de la operación
     */
    public boolean updateEvent(Event event) {
        if (event.getId() <= 0) {
            throw new IllegalArgumentException("ID de evento inválido");
        }
        return eventDB.update(event);
    }
    
    /**
     * Eliminar un evento
     * @param id ID del evento a eliminar
     * @return boolean resultado de la operación
     */
    public boolean deleteEvent(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de evento inválido");
        }
        return eventDB.delete(id);
    }
}
