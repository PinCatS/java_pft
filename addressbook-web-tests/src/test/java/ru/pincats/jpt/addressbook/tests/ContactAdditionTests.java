package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactAdditionTests extends TestBase {

    @Test
    public void testContactAddition() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData()
                                        .withFirstName("Sergey").withLastName("Li")
                                        .withNickname("pincats").withTitle("Principal Software Engineer")
                                        .withCompany("DELL EMC").withMobile("+79213120869")
                                        .withEmail("pincats@gmail.com").withGroup("test1");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        before.sort(app.contact().getComparatorById());
        after.sort(app.contact().getComparatorById());
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }

}
