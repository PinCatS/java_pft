package ru.pincats.jpt.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.pincats.jpt.addressbook.model.ContactData;
import ru.pincats.jpt.addressbook.model.Contacts;
import ru.pincats.jpt.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditionTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactFromXml() throws IOException {
        String xml = app.reader().readTestDataFrom("src/test/resources/contacts.xml");
        List<ContactData> contacts = (List<ContactData>) app.reader().fromXML(xml, ContactData.class);
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validContactFromJson() throws IOException {
        String json = app.reader().readTestDataFrom("src/test/resources/contacts.json");
        List<ContactData> contacts = (List<ContactData>) app.reader().fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactFromJson")
    public void testContactAddition(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        contact.withPhoto(new File("src/test/resources/sergei.jpg"));
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

}
