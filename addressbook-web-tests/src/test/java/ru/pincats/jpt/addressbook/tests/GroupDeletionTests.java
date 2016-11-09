package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int rand_index = app.getRandom().nextInt(before.size() - 1);
        app.getGroupHelper().selectGroup(rand_index);
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(rand_index);
        Assert.assertEquals(after, before);
    }

}
