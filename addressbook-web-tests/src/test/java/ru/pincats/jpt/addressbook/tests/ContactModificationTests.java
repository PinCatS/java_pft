package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        try {
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().selectContact();
            app.getContactHelper().initContactModification();
            app.getContactHelper().fillContactForm(new ContactData("Sergey2", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+7 921 312 08 69", "pincats@gmail.com"));
            app.getContactHelper().submitContactModification();
            app.getNavigationHelper().returnToHomePage();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            System.out.println("Stale reference detected. Try again");
            app.getContactHelper().selectContact();
            app.getContactHelper().initContactModification();
            app.getContactHelper().fillContactForm(new ContactData("Sergey2", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+7 921 312 08 69", "pincats@gmail.com"));
            app.getContactHelper().submitContactModification();
            app.getNavigationHelper().returnToHomePage();
        }
    }

}
