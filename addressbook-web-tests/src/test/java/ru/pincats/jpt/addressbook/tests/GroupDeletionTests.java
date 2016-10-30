package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        try{
            app.gotoGroupPage();
            app.selectGroup();
            app.deleteGroup();
            app.returnToGroupPage();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            System.out.println("Stale reference detected. Try again");
            app.selectGroup();
            app.deleteGroup();
            app.returnToGroupPage();
        }
    }

}
