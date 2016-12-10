package ru.pincats.jpt.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.pincats.jpt.mantis.model.MantisUser;

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

    public List<MantisUser> users() {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<MantisUser> result = session.createQuery( "from MantisUser where username <> 'administrator'" ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
