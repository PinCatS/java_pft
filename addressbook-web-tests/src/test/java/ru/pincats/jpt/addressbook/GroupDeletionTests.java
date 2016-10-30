package ru.pincats.jpt.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        try{
            gotoGroupPage();
            selectGroup();
            deleteGroup();
            returnToGroupPage();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            System.out.println("Stale reference detected. Try again");
            selectGroup();
            deleteGroup();
            returnToGroupPage();
        }
    }

}
