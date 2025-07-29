/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaServices;
import CapaDao.ContactDB;
import CapaModelo.Contact;
import java.util.List;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class ContactServices {
    private ContactDB contactDB;
    
    public ContactServices() {
        this.contactDB = new ContactDB();
    }
    
    /**
     * Validar y crear un nuevo contacto
     * @param contact contacto a crear
     * @return boolean resultado de la operación
     */
    public boolean createContact(Contact contact) {
        // Validaciones de negocio
        if (contact.getNombres() == null || contact.getNombres().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (contact.getApellidos() == null || contact.getApellidos().trim().isEmpty()) {
            throw new IllegalArgumentException("Los apellidos son obligatorios");
        }
        
        // Normalizar datos
        contact.setNombres(contact.getNombres().trim());
        contact.setApellidos(contact.getApellidos().trim());
        
        return contactDB.create(contact);
    }
    
    /**
     * Obtener todos los contactos
     * @return lista de contactos
     */
    public List<Contact> getAllContacts() {
        return contactDB.readAll();
    }
    
    /**
     * Actualizar un contacto existente
     * @param contact contacto con datos actualizados
     * @return boolean resultado de la operación
     */
    public boolean updateContact(Contact contact) {
        if (contact.getId() <= 0) {
            throw new IllegalArgumentException("ID de contacto inválido");
        }
        return contactDB.update(contact);
    }
    
    /**
     * Eliminar un contacto
     * @param id ID del contacto a eliminar
     * @return boolean resultado de la operación
     */
    public boolean deleteContact(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de contacto inválido");
        }
        return contactDB.delete(id);
    }
    
    /**
     * Buscar contactos por término
     * @param searchTerm término de búsqueda
     * @return lista de contactos encontrados
     */
    public List<Contact> searchContacts(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllContacts();
        }
        return contactDB.search(searchTerm.trim());
    }
}
