package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;
import ru.pincats.jpt.addressbook.model.Contacts;
import ru.pincats.jpt.addressbook.model.GroupData;
import ru.pincats.jpt.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by PinCatS on 04.12.2016.
 */
public class ContactAdditionToGroupTests extends TestBase {

    private ContactData contactToAddToGroup;
    private GroupData group;

    @BeforeMethod
    public void insurePrecondions() {
        if (app.db().contacts().size() == 0) {  // we don't have any contacts
            ContactData newContact = new ContactData().withFirstName("Sergey");
            app.contact().create(newContact);
        }
        contactToAddToGroup = app.db().contacts().iterator().next();

        int numberOfGroups = app.db().groups().size();
        // we don't have any groups or contact is already in all groups ==> create the new one
        if (numberOfGroups == 0 || contactToAddToGroup.getGroups().size() == numberOfGroups) {

            GroupData newGroup = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
            app.goTo().groupPage();
            app.group().create(newGroup);
            group = app.db().groups().iterator().next();
        }
        else if (numberOfGroups > 0) { // pick the right group in which there is no our contact
            Groups groups = app.db().groups();
            Set<GroupData> notAssignedGroups = new HashSet<GroupData>();
            for (GroupData g : groups) {
                Set<String> contactNamesInGroup = g.getContacts().stream().map((c) -> new String(c.getFirstName())).collect(Collectors.toSet());
                if (!contactNamesInGroup.contains(contactToAddToGroup.getFirstName())) {
                    notAssignedGroups.add(g);
                }
            }
            group = notAssignedGroups.iterator().next();
        }
    }

    @Test
    public void testContactAdditionToGroup() {
        app.goTo().homePage();
        Groups groupsBefore = contactToAddToGroup.getGroups();
        Contacts contactsBefore = group.getContacts();
        app.contact().addToGroup(contactToAddToGroup, group.getId());
        Groups groupsAfter =  app.db().contacts().getById(contactToAddToGroup.getId()).getGroups();
        Contacts contactsAfter = app.db().groups().getById(group.getId()).getContacts();

        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
        assertThat(contactsAfter.size(), equalTo(contactsBefore.size() + 1));
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(group)));
        assertThat(contactsAfter, equalTo(contactsBefore.withAdded(contactToAddToGroup)));
    }
}
