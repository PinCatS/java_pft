package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by PinCatS on 16.11.2016.
 */
public class ContactEmailsTests extends TestBase {

    @BeforeMethod
    public void insurePrecondions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                .withFirstName("Sergey").withLastName("Li")
                .withHomePhone("(812)535-68-62").withMobilePhone("+7(921)312-08-69").withWorkPhone("(812) 542 68 24")
                .withEmail("pincats@gmail.com").withEmail2("serj.li@emc.com").withEmail3("sweet-home@bro.eu"));
        }
    }

    @Test
    public void testContactEmails() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(cleaned(mergedEmails(contactInfoFromEditForm))));
    }

    private String mergedEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                    .filter((s) -> ! s.equals(""))
                    .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String email) {
        return email.replaceAll(" +"," "); // weird stuff...pincats@gmail. com is acceptable...
    }
}
