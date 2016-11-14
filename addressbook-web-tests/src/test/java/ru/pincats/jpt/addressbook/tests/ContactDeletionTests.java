package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Sergey"));
        }
    }

    @Test
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deletedData = before.iterator().next();
        app.contact().delete(deletedData);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedData);
        Assert.assertEquals(after, before);
    }
}
