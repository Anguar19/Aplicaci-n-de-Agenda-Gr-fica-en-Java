/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDao;
import java.sql.*;
import java.time.LocalDateTime;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class LogDAO {

    /**
     * Registra un error en la tabla "error_logs".
     * @param methodName Nombre del método donde ocurrió el error.
     * @param errorMessage Mensaje descriptivo del error.
     * 
     */
    public static void logError(String methodName, String errorMessage) {
        // Sentencia SQL para insertar el registro del error
        String sql = "INSERT INTO error_logs (fecha_hora, metodo, mensaje_error) VALUES (?, ?, ?)";

        try (
            // Obtiene conexión a la base de datos
            Connection conn = DatabaseConnection.getConnection();
            // Prepara la sentencia SQL
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // Coloca la fecha y hora actual
            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            // Coloca el nombre del método
            pstmt.setString(2, methodName);
            // Coloca el mensaje de error
            pstmt.setString(3, errorMessage);

            // Ejecuta la inserción en la base de datos
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Si falla el registro del error, imprime los detalles en la consola como respaldo
            System.err.println("Error en LogDAO: " + e.getMessage());
            System.err.println("Error original en " + methodName + ": " + errorMessage);
        }
    }
}
