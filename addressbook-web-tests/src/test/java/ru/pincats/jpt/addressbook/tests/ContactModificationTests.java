package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Sergey", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+7 921 312 08 69", "pincats@gmail.com", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactsList();
        app.getContactHelper().selectContact(app.getRandom().nextInt(before.size() - 1));
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Sergey2", null, null, null, null, null, null, null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size());
    }

}
