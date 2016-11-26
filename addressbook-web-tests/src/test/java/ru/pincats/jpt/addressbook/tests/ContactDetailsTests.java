package ru.pincats.jpt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by PinCatS on 17.11.2016.
 */
public class ContactDetailsTests extends TestBase {

    @BeforeMethod
    public void insurePrecondions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Sergey").withLastName("Li")
                    .withHomePhone("(812)535-68-62").withMobilePhone("+7(921)312-08-69").withWorkPhone("(812) 542 68 24")
                    .withHomePhone2("8 (812) 456 00 00")
                    .withEmail("pincats@gmail.com").withEmail2("serj.li@emc.com").withEmail3("sweet-home@bro.eu")
                    .withPostAddress("278 Old Gate Lane, Milford, CT 06460, USA"));
        }
    }

    @Test
    public void testContactDetails() {
        ContactData contact = app.db().contacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
        assertThat(mergeAllInfo(contactInfoFromEditForm), equalTo(contactInfoFromDetailsForm.getAllDetails()));
    }

    private String mergeAllInfo(ContactData contact) {
        return Stream.of(contact.getFirstName(), contact.getLastName(),
                    contact.getNickname(), contact.getTitle(),
                    contact.getCompany(), contact.getPostAddress(),
                    contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                    contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                    contact.getHomePhone2())
                .filter((s) -> ! s.equals(""))
                .map(ContactDetailsTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String details) {
        return details.replaceAll("[-()]","");
    }
}
