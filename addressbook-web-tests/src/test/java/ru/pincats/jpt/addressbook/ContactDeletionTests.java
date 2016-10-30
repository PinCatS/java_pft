package ru.pincats.jpt.addressbook;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        try{
            gotoHomePage();
            selectContact();
            deleteContact();
            gotoHomePage();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            System.out.println("Stale reference detected. Try again");
            selectContact();
            deleteContact();
            gotoHomePage();
        }
    }

}
