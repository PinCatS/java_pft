package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ApplicationManager {

    WebDriver wd;
    private final Random random = new Random(); // no need in seed because by default it initialize using time

    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private DbHelper dbHelper;

    private DataReader reader;
    private String browser;
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

        dbHelper = new DbHelper(properties.getProperty("db.hibernateConfigXML"));

        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        groupHelper = new GroupHelper(wd, this);
        contactHelper = new ContactHelper(wd, this);
        navigationHelper = new NavigationHelper(wd, this);
        sessionHelper = new SessionHelper(wd, this);
        reader = new DataReader();
        wd.get(properties.getProperty("web.baseUrl"));
        getSessionHelper().login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public Properties properties() {
        return properties;
    }

    public DataReader reader() {
        return reader;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public Random getRandom() {
        return random;
    }
}
