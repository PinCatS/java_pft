package ru.pincats.jpt.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.pincats.jpt.addressbook.appmanager.ApplictionManager;

/**
 * Created by PinCatS on 29.10.2016.
 */
public class TestBase {

    protected static final ApplictionManager app = new ApplictionManager(BrowserType.CHROME);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
