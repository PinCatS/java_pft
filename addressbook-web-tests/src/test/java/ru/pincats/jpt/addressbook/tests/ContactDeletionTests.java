package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        try{
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().selectContact();
            app.getContactHelper().deleteContact();
            app.getContactHelper().acceptContactDeletion();
            app.getNavigationHelper().gotoHomePage();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            System.out.println("Stale reference detected. Try again");
            app.getContactHelper().selectContact();
            app.getContactHelper().deleteContact();
            app.getContactHelper().acceptContactDeletion();
            app.getNavigationHelper().returnToHomePage();
        }
    }

}
