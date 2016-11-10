package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Sergey", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+79213120869", "pincats@gmail.com", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactsList();
        int random_index = app.getRandom().nextInt(before.size());
        app.getContactHelper().selectContact(random_index);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptContactDeletion();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(random_index);
        Assert.assertEquals(after, before);
    }

}
