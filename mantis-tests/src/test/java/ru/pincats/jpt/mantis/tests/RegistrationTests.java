package ru.pincats.jpt.mantis.tests;

import com.google.gson.reflect.TypeToken;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.pincats.jpt.mantis.model.MailMessage;
import ru.pincats.jpt.mantis.model.MantisUser;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by PinCatS on 10.12.2016.
 */
public class RegistrationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsersFromJson() throws IOException {
        // For now use manually randomly created one. In the future use UsersGenerator
        List<MantisUser> users = new ArrayList<>();
        long now = System.currentTimeMillis();
        MantisUser user = new MantisUser().withUsername(String.format("user%s", now))
                                        .withPassword("password")
                                        .withEmail(String.format("user%s@localhost.localdomain", now));
        users.add(user);
        return users.stream().map((u) -> new Object[] {u}).collect(Collectors.toList()).iterator();
    }

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test(dataProvider = "validUsersFromJson")
    public void testRegistation(MantisUser user) throws IOException, MessagingException {
        app.registration().start(user.getUsername(), user.getEmail());
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        app.registration().finish(confirmationLink, user.getPassword());
        assertTrue(app.newSession().login(user.getUsername(), user.getPassword()));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
