package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

public class ContactAdditionTests extends TestBase {

    @Test
    public void testContactAddition() {
        int before = app.getContactHelper().getContactsNumber();
        app.getContactHelper().createContact(new ContactData("Sergey", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+7 921 312 08 69", "pincats@gmail.com", "test1"));
        int after = app.getContactHelper().getContactsNumber();
        Assert.assertEquals(after, before + 1);
    }

}
