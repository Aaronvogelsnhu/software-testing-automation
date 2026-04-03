import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    // Add contact with unique ID
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null.");
        }
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Contact ID already exists: " + id);
        }
        contacts.put(id, contact);
    }

    // Delete contact by ID
    public void deleteContact(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID must not be null.");
        }
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("No contact found for ID: " + contactId);
        }
        contacts.remove(contactId);
    }

    // Update fields by ID (only these are updatable)
    public void updateFirstName(String contactId, String firstName) {
        Contact c = getExisting(contactId);
        c.setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        Contact c = getExisting(contactId);
        c.setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        Contact c = getExisting(contactId);
        c.setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        Contact c = getExisting(contactId);
        c.setAddress(address);
    }

    // Helpful for tests / verification
    public Contact getContact(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID must not be null.");
        }
        return contacts.get(contactId); // null if not found
    }

    public Map<String, Contact> getAllContactsReadOnly() {
        return Collections.unmodifiableMap(contacts);
    }

    private Contact getExisting(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID must not be null.");
        }
        Contact c = contacts.get(contactId);
        if (c == null) {
            throw new IllegalArgumentException("No contact found for ID: " + contactId);
        }
        return c;
    }
}