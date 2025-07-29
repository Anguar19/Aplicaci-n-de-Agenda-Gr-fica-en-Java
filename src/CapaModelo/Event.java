/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaModelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class Event {

    // Atributos del evento
    private int id;                     // Identificador único del evento
    private String descripcion;         // Descripción del evento
    private LocalDateTime fechaHora;    // Fecha y hora en que ocurre el evento
    private String ubicacion;           // Lugar donde se realizará el evento
    private int contactoId;             // ID del contacto asociado al evento

    /**
     * Constructor completo que inicializa todos los atributos del evento.
     * @param id Identificador único.
     * @param descripcion Descripción del evento.
     * @param fechaHora Fecha y hora del evento.
     * @param ubicacion Lugar donde se realizará el evento.
     * @param contactoId Identificador del contacto relacionado.
     */
    public Event(int id, String descripcion, LocalDateTime fechaHora, String ubicacion, int contactoId) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
        this.contactoId = contactoId;
    }

    /**
     * Constructor vacío no soportado.
     * (Actualmente lanza excepción indicando que no está implementado).
     */
    public Event() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Métodos getters y setters para acceder y modificar los atributos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getContactoId() {
        return contactoId;
    }

    public void setContactoId(int contactoId) {
        this.contactoId = contactoId;
    }

    /**
     * Devuelve una representación en texto del evento con todos sus atributos.
     * @return String con los datos del evento.
     */
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", descripcion=" + descripcion +
                ", fechaHora=" + fechaHora +
                ", ubicacion=" + ubicacion +
                ", contactoId=" + contactoId +
                '}';
    }
}
