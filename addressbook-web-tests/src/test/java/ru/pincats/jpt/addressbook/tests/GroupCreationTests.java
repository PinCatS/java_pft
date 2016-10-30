package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForms(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().returnToGroupPage();
    }

}
