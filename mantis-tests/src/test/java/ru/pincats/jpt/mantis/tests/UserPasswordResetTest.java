package ru.pincats.jpt.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.pincats.jpt.mantis.model.MailMessage;
import ru.pincats.jpt.mantis.model.MantisUser;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by PinCatS on 11.12.2016.
 */
public class UserPasswordResetTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testUserPasswordReset() throws IOException, MessagingException {
        app.admin().login(app.properties().getProperty("web.adminLogin"), app.properties().getProperty("web.adminPassword"));
        app.goTo().usersManagement();
        MantisUser user = app.db().users().iterator().next();
        app.admin().passwordResetFor(user);
        app.admin().logout();

        app.user().login(user.getUsername(), user.getPassword());
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        String newPassword = "password" + System.currentTimeMillis();
        app.user().finishReset(confirmationLink, newPassword);
        assertTrue(app.newSession().login(user.getUsername(), newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
