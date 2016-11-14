package ru.pincats.jpt.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by PinCatS on 15.11.2016.
 */
public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts);
    }

    @Override
    protected Set delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }

    public Contacts withModified(ContactData contact) {
        Contacts contacts = new Contacts(this);
        ContactData contactToDelete = null;
        for (ContactData c : contacts) {
            if (c.getId() == contact.getId())
                contactToDelete = c;
        }
        if (contactToDelete != null)
            contacts.remove(contactToDelete);

        contacts.add(contact);
        return contacts;
    }
}
