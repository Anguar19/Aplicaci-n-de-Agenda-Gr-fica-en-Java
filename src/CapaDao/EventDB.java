/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDao;
import CapaModelo.Event;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class EventDB {

    /**
     * Crea un nuevo evento en la base de datos.
     * @param event Objeto Event con la información a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean create(Event event) {
        String sql = "INSERT INTO events (descripcion, fecha_hora, ubicacion, contacto_id) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = DatabaseConnection.getConnection(); // Obtiene conexión a la base de datos
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) // Prepara la sentencia SQL
        ) {
            // Asigna los valores a los parámetros del SQL
            pstmt.setString(1, event.getDescripcion());
            pstmt.setTimestamp(2, Timestamp.valueOf(event.getFechaHora()));
            pstmt.setString(3, event.getUbicacion());
            pstmt.setInt(4, event.getContactoId());

            int affectedRows = pstmt.executeUpdate(); // Ejecuta la inserción

            // Si al menos una fila fue afectada, obtiene el ID generado
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        event.setId(generatedKeys.getInt(1)); // Asigna el ID generado al objeto
                    }
                }
                return true; // Inserción exitosa
            }
        } catch (SQLException e) {
            // Manejo de errores
            LogDAO.logError("EventDAO.create", e.getMessage());
            e.printStackTrace();
        }
        return false; // Inserción fallida
    }

    /**
     * Recupera todos los eventos de la base de datos ordenados por fecha.
     * @return Lista de objetos Event.
     */
    public List<Event> readAll() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events ORDER BY fecha_hora";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery() // Ejecuta consulta
        ) {
            // Recorre los resultados y crea objetos Event
            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setDescripcion(rs.getString("descripcion"));
                event.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                event.setUbicacion(rs.getString("ubicacion"));
                event.setContactoId(rs.getInt("contacto_id"));
                events.add(event); // Agrega el evento a la lista
            }
        } catch (SQLException e) {
            LogDAO.logError("EventDAO.readAll", e.getMessage());
            e.printStackTrace();
        }

        return events; // Retorna la lista de eventos
    }

    /**
     * Recupera los eventos próximos (dentro de los próximos 30 días).
     * @return Lista de eventos que ocurren en los próximos 30 días.
     */
    public List<Event> getUpcomingEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE fecha_hora >= NOW() AND fecha_hora <= NOW() + INTERVAL '30 days' ORDER BY fecha_hora";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {
            // Recorre los resultados y agrega eventos futuros
            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setDescripcion(rs.getString("descripcion"));
                event.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                event.setUbicacion(rs.getString("ubicacion"));
                event.setContactoId(rs.getInt("contacto_id"));
                events.add(event);
            }
        } catch (SQLException e) {
            LogDAO.logError("EventDAO.getUpcomingEvents", e.getMessage());
            e.printStackTrace();
        }

        return events; // Retorna eventos futuros
    }

    /**
     * Actualiza los datos de un evento existente.
     * @param event Objeto Event con la información actualizada.
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    public boolean update(Event event) {
        String sql = "UPDATE events SET descripcion=?, fecha_hora=?, ubicacion=?, contacto_id=? WHERE id=?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // Asigna nuevos valores
            pstmt.setString(1, event.getDescripcion());
            pstmt.setTimestamp(2, Timestamp.valueOf(event.getFechaHora()));
            pstmt.setString(3, event.getUbicacion());
            pstmt.setInt(4, event.getContactoId());
            pstmt.setInt(5, event.getId());

            return pstmt.executeUpdate() > 0; // Devuelve true si al menos una fila fue actualizada
        } catch (SQLException e) {
            LogDAO.logError("EventDAO.update", e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Elimina un evento de la base de datos por su ID.
     * @param id Identificador del evento a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM events WHERE id=?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0; // Retorna true si se eliminó al menos una fila
        } catch (SQLException e) {
            LogDAO.logError("EventDAO.delete", e.getMessage());
            e.printStackTrace();
        }

        return false;
    }
}
