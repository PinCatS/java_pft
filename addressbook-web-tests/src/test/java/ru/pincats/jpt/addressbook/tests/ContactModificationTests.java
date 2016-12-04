package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;
import ru.pincats.jpt.addressbook.model.Contacts;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void insurePrecondions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Sergey"));
        }
    }

    @Test
    public void testContactModification() throws IllegalAccessException {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstName("Sergey2").withLastName("Li")
                .withNickname("pincats").withTitle("Principal Software Engineer")
                .withCompany("DELL EMC").withMobilePhone("+79213120869")
                .withPostAddress("278 Old Gate Lane, Milford, CT 06460, USA")
                .withHomePhone("(812)535-68-62").withHomePhone2("8 (812) 456 00 00").withWorkPhone("(812) 542 68 24")
                .withEmail("pincats@gmail.com").withEmail2("serj.li@emc.com").withEmail3("sweet-home@bro.eu");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        ContactData.setStringNullMembersToEmpty(contact);
        assertThat(after, equalTo(before.withModified(contact)));
        verifyContactListInUI();
    }
}
