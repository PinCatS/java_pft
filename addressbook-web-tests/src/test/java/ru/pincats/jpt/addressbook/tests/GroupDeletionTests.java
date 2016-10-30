package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        try{
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().selectGroup();
            app.getGroupHelper().deleteGroup();
            app.getNavigationHelper().returnToGroupPage();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            System.out.println("Stale reference detected. Try again");
            app.getGroupHelper().selectGroup();
            app.getGroupHelper().deleteGroup();
            app.getNavigationHelper().returnToGroupPage();
        }
    }

}
