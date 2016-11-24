package ru.pincats.jpt.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.pincats.jpt.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by PinCatS on 29.10.2016.
 */
public class TestBase {

    private Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logStartTest(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with parms " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logFinishTest(Method m) {
        logger.info("Finish test " + m.getName());
    }

}
