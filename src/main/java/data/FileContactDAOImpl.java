package data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import exception.ContactStorageException;
import model.Contact;


/**
 * File-based implementation of ContactDAO.
 * Stores each contact as a CSV line: name,phoneNumber
 */

public class FileContactDAOImpl implements ContactDAO {

    private final Path filePath = Paths.get("contacts.csv");

    @Override
    /** Reads all contacts from the file. Returns empty list if file does not exist or is empty.
     * @throws ContactStorageException when file access or parsing fails.
     */
    public List<Contact> findAll() throws ContactStorageException {
        try {
            List<Contact> contacts = new ArrayList<>();
            if (!Files.exists(filePath) || Files.size(filePath) == 0) {
                return contacts;
            }
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    contacts.add(new Contact(parts[0].trim(), parts[1].trim()));
                }
            }
            return contacts;
        } catch (Exception e) {
            throw new ContactStorageException("Reading file failed: " + e.getMessage());
        }
    }

    @Override
    /** Adds a contact by appending to the file
     * Duplicate handling is not performed here.
     * @throws ContactStorageException on IO failure.
     */
    public void save(Contact contact) throws ContactStorageException {
        try {
            String line = contact.getName() + "," + contact.getPhoneNumber() + System.lineSeparator();
            Files.write(
                filePath,
                line.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (Exception e) {
            throw new ContactStorageException("Failed saving contact: " + e.getMessage());
        }
    }

    @Override
    /** Finds a contact by name (case-insensitive).
     * @throws ContactStorageException if not found or on IO failure.
     */
    public Contact findByName(String name) throws ContactStorageException {
        List<Contact> contacts = findAll();
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        throw new ContactStorageException("Contact not found: " + name);
    }
}