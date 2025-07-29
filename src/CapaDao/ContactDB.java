/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDao;
import CapaModelo.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * 
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class ContactDB {

    /**
     * Crea un nuevo contacto en la base de datos.
     * 
     * @param contact El objeto Contact con los datos a insertar.
     * 
     * @return true si el contacto fue creado exitosamente, false en caso de error.
     */
    public boolean create(Contact contact) {
        // Consulta SQL para insertar un nuevo contacto
        String sql = "INSERT INTO contacts (nombres, apellidos, telefonos, emails, direccion, etiquetas, grupo_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
            // Obtener conexión a la base de datos
            Connection conn = DatabaseConnection.getConnection();
            // Preparar la sentencia SQL con retorno de claves generadas (ID)
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            // Asignar los valores de los parámetros
            pstmt.setString(1, contact.getNombres());
            pstmt.setString(2, contact.getApellidos());
            pstmt.setString(3, contact.getTelefonos());
            pstmt.setString(4, contact.getEmails());
            pstmt.setString(5, contact.getDireccion());
            pstmt.setString(6, contact.getEtiquetas());
            pstmt.setInt(7, contact.getGrupoId());

            // Ejecutar la sentencia
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // Obtener el ID generado automáticamente y asignarlo al contacto
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        contact.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            LogDAO.logError("ContactDAO.create", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Lee todos los contactos de la base de datos, ordenados por nombre y apellido.
     * 
     * @return Lista de objetos Contact obtenidos desde la base de datos.
     */
    public List<Contact> readAll() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts ORDER BY nombres, apellidos";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {
            // Recorrer los resultados y convertirlos a objetos Contact
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setNombres(rs.getString("nombres"));
                contact.setApellidos(rs.getString("apellidos"));
                contact.setTelefonos(rs.getString("telefonos"));
                contact.setEmails(rs.getString("emails"));
                contact.setDireccion(rs.getString("direccion"));
                contact.setEtiquetas(rs.getString("etiquetas"));
                contact.setGrupoId(rs.getInt("grupo_id"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            LogDAO.logError("ContactDAO.readAll", e.getMessage());
            e.printStackTrace();
        }
        return contacts;
    }

    /**
     * Actualiza un contacto existente en la base de datos.
     * 
     * @param contact Objeto Contact con datos actualizados (debe contener el ID).
     * @return true si la operación fue exitosa, false si falló.
     */
    public boolean update(Contact contact) {
        String sql = "UPDATE contacts SET nombres=?, apellidos=?, telefonos=?, emails=?, direccion=?, etiquetas=?, grupo_id=? WHERE id=?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // Asignar los valores actualizados
            pstmt.setString(1, contact.getNombres());
            pstmt.setString(2, contact.getApellidos());
            pstmt.setString(3, contact.getTelefonos());
            pstmt.setString(4, contact.getEmails());
            pstmt.setString(5, contact.getDireccion());
            pstmt.setString(6, contact.getEtiquetas());
            pstmt.setInt(7, contact.getGrupoId());
            pstmt.setInt(8, contact.getId());

            // Ejecutar y verificar si se actualizó al menos una fila
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LogDAO.logError("ContactDAO.update", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un contacto de la base de datos mediante su ID.
     * 
     * @param id Identificador del contacto a eliminar.
     * @return true si la eliminación fue exitosa, false en caso de error.
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM contacts WHERE id=?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LogDAO.logError("ContactDAO.delete", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Busca contactos que coincidan con el término de búsqueda en nombres, apellidos,
     * teléfonos, correos o etiquetas.
     * 
     * @param searchTerm Término de búsqueda parcial (puede ser parte de un nombre, etc.).
     * @return Lista de contactos que coinciden con el criterio.
     */
    public List<Contact> search(String searchTerm) {
        List<Contact> contacts = new ArrayList<>();
        // ILIKE permite búsquedas insensibles a mayúsculas (útil en PostgreSQL)
        String sql = "SELECT * FROM contacts WHERE nombres ILIKE ? OR apellidos ILIKE ? OR telefonos ILIKE ? OR emails ILIKE ? OR etiquetas ILIKE ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // Agregar comodines para búsqueda parcial
            String term = "%" + searchTerm + "%";
            for (int i = 1; i <= 5; i++) {
                pstmt.setString(i, term);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Contact contact = new Contact();
                    contact.setId(rs.getInt("id"));
                    contact.setNombres(rs.getString("nombres"));
                    contact.setApellidos(rs.getString("apellidos"));
                    contact.setTelefonos(rs.getString("telefonos"));
                    contact.setEmails(rs.getString("emails"));
                    contact.setDireccion(rs.getString("direccion"));
                    contact.setEtiquetas(rs.getString("etiquetas"));
                    contact.setGrupoId(rs.getInt("grupo_id"));
                    contacts.add(contact);
                }
            }
        } catch (SQLException e) {
            LogDAO.logError("ContactDAO.search", e.getMessage());
            e.printStackTrace();
        }
        return contacts;
    }
}