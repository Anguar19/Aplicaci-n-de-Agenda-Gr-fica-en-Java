/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaModelo;

/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class Group {

    // Atributos que describen un grupo
    private int id;               // Identificador único del grupo
    private String nombre;        // Nombre del grupo
    private String descripcion;   // Descripción del grupo
    private String color;         // Color asociado al grupo (por ejemplo, para UI)

    /**
     * Constructor que inicializa todos los atributos del grupo.
     * @param id Identificador único del grupo.
     * @param nombre Nombre del grupo.
     * @param descripcion Descripción del grupo.
     * @param color Color asociado al grupo.
     */
    public Group(int id, String nombre, String descripcion, String color) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.color = color;
    }

    // Métodos getters y setters para acceder y modificar los atributos privados

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Devuelve una representación en texto del grupo con todos sus atributos.
     * @return String con los datos del grupo.
     */
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", descripcion=" + descripcion +
                ", color=" + color +
                '}';
    }
}
