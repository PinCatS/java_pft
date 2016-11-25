package ru.pincats.jpt.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.pincats.jpt.addressbook.model.ContactData;
import ru.pincats.jpt.addressbook.model.Contacts;
import ru.pincats.jpt.addressbook.model.GroupData;
import ru.pincats.jpt.addressbook.model.Groups;

import java.util.List;

/**
 * Created by PinCatS on 26.11.2016.
 */
public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper(String resourceName) {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(resourceName)
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Contacts contacts() {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public Groups groups() {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }
}
