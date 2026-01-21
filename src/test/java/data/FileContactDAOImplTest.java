package data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import exception.ContactStorageException;
import model.Contact;

class FileContactDAOImplTest {

    @TempDir
    Path tempDir;

    private Path contactsFile;
    private FileContactDAOImpl dao;

    @BeforeEach
    void setUp() {
        contactsFile = tempDir.resolve("contacts.csv");
        deleteIfExistsQuietly(contactsFile);
        dao = new FileContactDAOImpl();
        injectPath(dao, contactsFile);
    }

    @Test
    void findAllReturnsEmptyListWhenFileMissing() throws ContactStorageException {
        List<Contact> contacts = dao.findAll();
        assertTrue(contacts.isEmpty());
    }

    @Test
    void saveAppendsAndFindAllReturnsContacts() throws ContactStorageException {
        dao.save(new Contact("Alice", "0123456789"));
        dao.save(new Contact("Bob", "1111111111"));

        List<Contact> contacts = dao.findAll();
        assertEquals(2, contacts.size());
        assertEquals("Alice", contacts.get(0).getName());
        assertEquals("Bob", contacts.get(1).getName());
    }

    @Test
    void findByNameReturnsMatchingContact() throws ContactStorageException {
        dao.save(new Contact("Alice", "0123456789"));
        Contact found = dao.findByName("alice");
        assertEquals("0123456789", found.getPhoneNumber());
    }

    @Test
    void findByNameThrowsWhenMissing() throws ContactStorageException {
        dao.save(new Contact("Alice", "0123456789"));
        assertThrows(ContactStorageException.class, () -> dao.findByName("Bob"));
    }

    @Test
    void saveThrowsWhenPathIsDirectory() throws IOException {
        deleteIfExistsQuietly(contactsFile);
        Files.createDirectory(contactsFile); // create directory where file is expected
        FileContactDAOImpl badDao = new FileContactDAOImpl();
        injectPath(badDao, contactsFile);
        assertThrows(ContactStorageException.class, () -> badDao.save(new Contact("Alice", "0123456789")));
    }

    private void deleteIfExistsQuietly(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException ignored) {
            // ignore
        }
    }

    private void injectPath(FileContactDAOImpl target, Path path) {
        try {
            Field field = FileContactDAOImpl.class.getDeclaredField("filePath");
            field.setAccessible(true);
            field.set(target, path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject path for testing", e);
        }
    }
}
