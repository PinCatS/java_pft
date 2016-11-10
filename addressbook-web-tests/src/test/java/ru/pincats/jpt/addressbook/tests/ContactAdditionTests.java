package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactAdditionTests extends TestBase {

    @Test
    public void testContactAddition() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactsList();
        ContactData contact = new ContactData("Sergey", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+79213120869", "pincats@gmail.com", "test1");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int max = -1;
        for (ContactData c : after) {
            int id = c.getId();
            if (id > max)
                max = id;
        }
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }

}
