import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setup() {
        service = new ContactService();
    }

    @Test
    void testAddContactStoresContact() {
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");
        service.addContact(c);

        Contact stored = service.getContact("1");
        assertNotNull(stored);
        assertEquals("A", stored.getFirstName());
    }

    @Test
    void testAddContactRequiresUniqueId() {
        Contact c1 = new Contact("1", "A", "B", "1234567890", "Addr");
        Contact c2 = new Contact("1", "C", "D", "0987654321", "Addr2");

        service.addContact(c1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(c2));
    }

    @Test
    void testDeleteContactRemovesContact() {
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");
        service.addContact(c);

        service.deleteContact("1");
        assertNull(service.getContact("1"));
    }

    @Test
    void testDeleteContactNonexistentThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("missing"));
    }

    @Test
    void testUpdateFieldsById() {
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");
        service.addContact(c);

        service.updateFirstName("1", "Aaron");
        service.updateLastName("1", "Vogel");
        service.updatePhone("1", "0987654321");
        service.updateAddress("1", "123 Main Street");

        Contact updated = service.getContact("1");
        assertEquals("Aaron", updated.getFirstName());
        assertEquals("Vogel", updated.getLastName());
        assertEquals("0987654321", updated.getPhone());
        assertEquals("123 Main Street", updated.getAddress());
    }

    @Test
    void testUpdateNonexistentIdThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("missing", "X"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("missing", "X"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("missing", "1234567890"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("missing", "Addr"));
    }

    @Test
    void testUpdateUsesSameValidationRules() {
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");
        service.addContact(c);

        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("1", "12345678901"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "123"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("1", "1234567890123456789012345678901"));
    }
}
