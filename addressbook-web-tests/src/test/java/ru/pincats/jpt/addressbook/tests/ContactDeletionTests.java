package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        try{
            app.gotoHomePage();
            app.selectContact();
            app.deleteContact();
            app.gotoHomePage();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            System.out.println("Stale reference detected. Try again");
            app.selectContact();
            app.deleteContact();
            app.gotoHomePage();
        }
    }

}
