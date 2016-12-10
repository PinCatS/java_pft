package ru.pincats.jpt.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by PinCatS on 10.12.2016.
 */
public class RegistrationTests extends TestBase {

    @Test
    public void testRegistation() {
        app.registration().start("user1", "user1@localhost.localdomain");

    }
}
