package ru.pincats.jpt.addressbook;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        gotoHomePage();
        selectContact();
        deleteContact();
    }

}
