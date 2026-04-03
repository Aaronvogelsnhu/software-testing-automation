import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    void testValidContactCreation() {
        Contact c = new Contact("12345", "Aaron", "Vogel", "1234567890", "123 Main St, Vallejo");
        assertEquals("12345", c.getContactId());
        assertEquals("Aaron", c.getFirstName());
        assertEquals("Vogel", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St, Vallejo", c.getAddress());
    }

    @Test
    void testContactIdCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, "A", "B", "1234567890", "Addr"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345678901", "A", "B", "1234567890", "Addr"));
    }

    @Test
    void testFirstNameValidation() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", null, "B", "1234567890", "Addr"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "12345678901", "B", "1234567890", "Addr"));
    }

    @Test
    void testLastNameValidation() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "A", null, "1234567890", "Addr"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "A", "12345678901", "1234567890", "Addr"));
    }

    @Test
    void testPhoneMustBeExactly10Digits() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "A", "B", null, "Addr"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "A", "B", "123", "Addr"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "A", "B", "12345678901", "Addr"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "A", "B", "12345abcde", "Addr"));
    }

    @Test
    void testAddressValidation() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "A", "B", "1234567890", null));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "A", "B", "1234567890", "1234567890123456789012345678901")); // 31 chars
    }

    @Test
    void testSettersValidateAndUpdate() {
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");

        c.setFirstName("NewName");
        assertEquals("NewName", c.getFirstName());

        c.setLastName("NewLast");
        assertEquals("NewLast", c.getLastName());

        c.setPhone("0987654321");
        assertEquals("0987654321", c.getPhone());

        c.setAddress("New Address");
        assertEquals("New Address", c.getAddress());

        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("notdigits!!"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("1234567890123456789012345678901"));
    }
}
