package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.GroupData;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        int before = app.getGroupHelper().getGroupNumber();
        app.getGroupHelper().selectGroup(app.getRandom().nextInt(before -1));
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForms(new GroupData("test1", null, null));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupNumber();
        Assert.assertEquals(after, before);
    }

}
