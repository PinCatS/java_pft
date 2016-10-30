package ru.pincats.jpt.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.pincats.jpt.addressbook.appmanager.ApplictionManager;

/**
 * Created by PinCatS on 29.10.2016.
 */
public class TestBase {

    protected final ApplictionManager app = new ApplictionManager(BrowserType.CHROME);

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

}
