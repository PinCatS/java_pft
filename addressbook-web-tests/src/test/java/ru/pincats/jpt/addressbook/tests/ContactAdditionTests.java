package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactAdditionTests extends TestBase {

    @Test
    public void testContactAddition() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                                        .withFirstName("Sergey").withLastName("Li")
                                        .withNickname("pincats").withTitle("Principal Software Engineer")
                                        .withCompany("DELL EMC").withMobile("+79213120869")
                                        .withEmail("pincats@gmail.com").withGroup("test1");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }

}
