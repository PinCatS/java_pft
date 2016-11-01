package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

public class ContactAdditionTests extends TestBase {

    @Test
    public void testContactAddition() {
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().fillContactForm(new ContactData("Sergey", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+7 921 312 08 69", "pincats@gmail.com", "test1"), true);
        app.getContactHelper().submitAddNewForm();
        app.getNavigationHelper().returnToHomePage();
    }

}
