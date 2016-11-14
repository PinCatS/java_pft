package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void insurePrecondions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Sergey"));
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstName("Sergey2").withLastName("Li")
                .withNickname("pincats").withTitle("Principal Software Engineer")
                .withCompany("DELL EMC").withMobile("+79213120869")
                .withEmail("pincats@gmail.com").withGroup("test1");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);

        Assert.assertEquals(after, before);
    }
}
