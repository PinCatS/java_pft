package ru.pincats.jpt.mantis.tests;

import org.testng.annotations.Test;
import ru.pincats.jpt.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by PinCatS on 10.12.2016.
 */
public class LoginTests extends TestBase {
    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "aquanox123"));
        assertTrue(session.isLoggedInAs("administrator"));

    }
}
