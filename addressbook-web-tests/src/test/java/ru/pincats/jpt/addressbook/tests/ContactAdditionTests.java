package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.List;

public class ContactAdditionTests extends TestBase {

    @Test
    public void testContactAddition() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactsList();
        app.getContactHelper().createContact(new ContactData("Sergey", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+7 921 312 08 69", "pincats@gmail.com", "test1"));
        List<ContactData> after = app.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
