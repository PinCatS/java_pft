package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by PinCatS on 17.11.2016.
 */
public class ContactPostAddressTests extends TestBase {

    @BeforeMethod
    public void insurePrecondions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                .withFirstName("Sergey").withLastName("Li")
                .withHomePhone("(812)535-68-62").withMobilePhone("+7(921)312-08-69").withWorkPhone("(812) 542 68 24")
                .withHomePhone2("8 (812) 456 00 00")
                .withEmail("pincats@gmail.com").withEmail2("serj.li@emc.com").withEmail3("sweet-home@bro.eu")
                .withPostAddress("278 Old Gate Lane, Milford, CT 06460, USA"));
        }
    }

    @Test
    public void testContactEmails() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getPostAddress(), equalTo(contactInfoFromEditForm.getPostAddress()));
    }
}
