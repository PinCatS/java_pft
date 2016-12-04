package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;
import ru.pincats.jpt.addressbook.model.Contacts;
import ru.pincats.jpt.addressbook.model.GroupData;
import ru.pincats.jpt.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by PinCatS on 05.12.2016.
 */
public class ContactDeletionFromGroupTests extends TestBase {

    private ContactData contactToRemoveFromGroup;
    private GroupData group;

    @BeforeMethod
    public void insurePrecondions() {
        if (app.db().contacts().size() == 0) {  // we don't have any contacts
            ContactData newContact = new ContactData().withFirstName("Sergey");
            app.contact().create(newContact);
        }
        contactToRemoveFromGroup = app.db().contacts().iterator().next();

        int numberOfGroups = app.db().groups().size();
        // we don't have any groups, let's create one
        if (numberOfGroups == 0) {
            GroupData newGroup = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
            app.goTo().groupPage();
            app.group().create(newGroup);
            group = app.db().groups().iterator().next();

            // assign newly created group to contact
            app.goTo().homePage();
            app.contact().addToGroup(contactToRemoveFromGroup, group.getId());
        }

        if (contactToRemoveFromGroup.getGroups().size() > 0) {
            // pick one which is assigned
            group = contactToRemoveFromGroup.getGroups().iterator().next();
        }
        else {
            // assign one
            group = app.db().groups().iterator().next();
            app.goTo().homePage();
            app.contact().addToGroup(contactToRemoveFromGroup, group.getId());
        }
    }

    @Test
    public void testContactAdditionToGroup() {
        app.goTo().homePage();
        Groups groupsBefore =  app.db().contacts().getById(contactToRemoveFromGroup.getId()).getGroups();
        Contacts contactsBefore = app.db().groups().getById(group.getId()).getContacts();
        app.contact().deleteFromGroup(contactToRemoveFromGroup, group.getId());
        Groups groupsAfter =  app.db().contacts().getById(contactToRemoveFromGroup.getId()).getGroups();
        Contacts contactsAfter = app.db().groups().getById(group.getId()).getContacts();

        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
        assertThat(contactsAfter.size(), equalTo(contactsBefore.size() - 1));
        assertThat(groupsAfter, equalTo(groupsBefore.without(group)));
        assertThat(contactsAfter, equalTo(contactsBefore.without(contactToRemoveFromGroup)));
    }
}
