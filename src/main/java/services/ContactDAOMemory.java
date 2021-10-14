package services;

import data.Contact;

public class ContactDAOMemory implements ContactDAO {
    @Override
    public boolean create(Contact contact) {
        return false;
    }

    @Override
    public Contact retrieve(String nif) {
        return null;
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public Contact delete(String nif) {
        return null;
    }
}
