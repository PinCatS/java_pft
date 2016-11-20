package ru.pincats.jpt.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.pincats.jpt.addressbook.model.ContactData;
import ru.pincats.jpt.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PinCatS on 20.11.2016.
 */
public class ContactDataGenerator extends DataGenerator<ContactData> {
    @Parameter(names = "-c", description = "Contacts count")
    private int count;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generate(count);
        save(contacts, file, format);
    }

    private static List<ContactData> generate(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            ContactData contact = new ContactData()
                    .withFirstName("Sergey" + i).withLastName("Li")
                    .withHomePhone("(812)535-68-62").withMobilePhone("+7(921)312-08-69").withWorkPhone("(812) 542 68 24")
                    .withHomePhone2("8 (812) 456 00 00")
                    .withEmail("pincats" + i + "@gmail.com").withEmail2("serj.li@emc.com").withEmail3("sweet-home@bro.eu")
                    .withPostAddress("278 Old Gate Lane, Milford, CT 06460, USA");
            contacts.add(contact);
        }
        return contacts;
    }
}
