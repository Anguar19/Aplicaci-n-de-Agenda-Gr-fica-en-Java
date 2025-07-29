/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaModelo;

/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class Contact {

    // Atributos que representan las propiedades del contacto
    private int id;              // Identificador único del contacto
    private String nombres;      // Nombres del contacto
    private String apellidos;    // Apellidos del contacto
    private String telefonos;    // Números telefónicos asociados
    private String emails;       // Correos electrónicos asociados
    private String direccion;    // Dirección física del contacto
    private String etiquetas;    // Etiquetas o categorías para el contacto
    private int grupoId;         // Identificador del grupo al que pertenece el contacto

    /**
     * Constructor completo que inicializa todos los atributos del contacto.
     * @param id Identificador único del contacto.
     * @param nombres Nombres del contacto.
     * @param apellidos Apellidos del contacto.
     * @param telefonos Números telefónicos.
     * @param emails Correos electrónicos.
     * @param direccion Dirección física.
     * @param etiquetas Etiquetas o categorías.
     * @param grupoId Identificador del grupo asociado.
     * 
     */
    public Contact(int id, String nombres, String apellidos, String telefonos, String emails, String direccion, String etiquetas, int grupoId) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefonos = telefonos;
        this.emails = emails;
        this.direccion = direccion;
        this.etiquetas = etiquetas;
        this.grupoId = grupoId;
    }

    /**
     * Constructor vacío no soportado. 
     * (Actualmente lanza excepción indicando que no está implementado).
     */
    public Contact() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Métodos getters y setters para acceder y modificar los atributos privados

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    /**
     * Método que devuelve una representación en cadena con todos los datos del contacto.
     * @return String con información completa del contacto.
     */
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nombres=" + nombres +
                ", apellidos=" + apellidos +
                ", telefonos=" + telefonos +
                ", emails=" + emails +
                ", direccion=" + direccion +
                ", etiquetas=" + etiquetas +
                ", grupoId=" + grupoId +
                '}';
    }
}
