package data;

import java.util.List;

import exception.ContactStorageException;
import model.Contact;

public interface ContactDAO {
    
    List<Contact> findAll() throws ContactStorageException;
    void save(Contact contact) throws ContactStorageException;
    Contact findByName(String name) throws ContactStorageException;
}
