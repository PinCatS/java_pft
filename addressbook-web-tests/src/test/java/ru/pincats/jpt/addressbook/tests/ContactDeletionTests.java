package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;
import ru.pincats.jpt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Sergey"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedData = before.iterator().next();
        app.contact().delete(deletedData);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedData)));
    }
}
