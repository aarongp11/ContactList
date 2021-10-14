package services;

import data.Contact;

public interface ContactDAO {
    boolean create(Contact contact);
    Contact retrieve(String nif);
    Contact update(Contact contact);
    Contact delete(String nif);
}
