package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ContactTest {

    @Test
    void constructorStoresValidValues() {
        Contact contact = new Contact("Alice", "0123456789");
        assertEquals("Alice", contact.getName());
        assertEquals("0123456789", contact.getPhoneNumber());
    }

    @Test
    void constructorRejectsNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "0123456789"));
    }

    @Test
    void constructorRejectsBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("   ", "0123456789"));
    }

    @Test
    void constructorRejectsNullPhone() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("Alice", null));
    }

    @Test
    void constructorRejectsBlankPhone() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("Alice", "   "));
    }

    @Test
    void constructorRejectsNonDigitPhone() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("Alice", "01234abcde"));
    }

    @Test
    void constructorRejectsWrongLengthPhone() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("Alice", "012345678"));
    }
}
