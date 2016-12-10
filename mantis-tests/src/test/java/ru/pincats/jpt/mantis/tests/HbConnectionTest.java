package ru.pincats.jpt.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.pincats.jpt.mantis.model.MantisUser;

import java.util.List;

/**
 * Created by PinCatS on 25.11.2016.
 */
public class HbConnectionTest {

    SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
            return;
        }
    }

    @Test
    public void testHbConnection() {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<MantisUser> result = session.createQuery( "from MantisUser where username <> 'administrator'" ).list();
        session.getTransaction().commit();
        session.close();

        for ( MantisUser user : result ) {
            System.out.println(user);
        }
    }
}
