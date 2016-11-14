package ru.pincats.jpt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void insurePrecondions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Sergey"));
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        int random_index = app.getRandom().nextInt(before.size());
        ContactData modified_contact = new ContactData().withId(before.get(random_index).getId())
                .withFirstName("Sergey2").withLastName("Li")
                .withNickname("pincats").withTitle("Principal Software Engineer")
                .withCompany("DELL EMC").withMobile("+79213120869")
                .withEmail("pincats@gmail.com").withGroup("test1");
        app.contact().modify(random_index, modified_contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(random_index);
        before.add(modified_contact);

        before.sort(app.contact().getComparatorById());
        after.sort(app.contact().getComparatorById());
        Assert.assertEquals(after, before);
    }
}
