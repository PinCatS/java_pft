package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int random_index = app.getRandom().nextInt(before.size());
        GroupData new_group = new GroupData(before.get(random_index).getId(), "test9", null, null);
        app.getGroupHelper().modifyGroup(random_index, new_group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(random_index);
        before.add(new_group); // note: we obtain only name elem of group data, that is why I can use here new_group

        before.sort(app.getGroupHelper().getComparatorById());
        after.sort(app.getGroupHelper().getComparatorById());
        Assert.assertEquals(after, before);
    }
}
