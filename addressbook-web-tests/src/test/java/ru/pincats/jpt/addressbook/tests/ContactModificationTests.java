package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Sergey", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+79213120869", "pincats@gmail.com", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactsList();
        int random_index = app.getRandom().nextInt(before.size() - 1);
        app.getContactHelper().initContactModification(random_index);
        ContactData new_contact = new ContactData(before.get(random_index).getId(),"Sergey2", null, null, null, null, null, null, null);
        app.getContactHelper().fillContactForm(new_contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size());

        ContactData modified_contact = new ContactData(before.get(random_index).getId(), "Sergey2", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+79213120869", "pincats@gmail.com", "test1");
        before.remove(random_index);
        before.add(modified_contact);

        before.sort(app.getContactHelper().getComparatorById());
        after.sort(app.getContactHelper().getComparatorById());
        Assert.assertEquals(after, before);
    }

}
