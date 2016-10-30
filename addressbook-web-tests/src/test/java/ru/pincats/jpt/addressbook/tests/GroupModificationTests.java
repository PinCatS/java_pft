package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.GroupData;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        try {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().selectGroup();
            app.getGroupHelper().initGroupModification();
            app.getGroupHelper().fillGroupForms(new GroupData("test1", "test2", "test3"));
            app.getGroupHelper().submitGroupModification();
            app.getNavigationHelper().returnToGroupPage();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            System.out.println("Stale reference detected. Try again");
            app.getGroupHelper().selectGroup();
            app.getGroupHelper().initGroupModification();
            app.getGroupHelper().fillGroupForms(new GroupData("test1", "test2", "test3"));
            app.getGroupHelper().submitGroupModification();
            app.getNavigationHelper().returnToGroupPage();

        }
    }

}
