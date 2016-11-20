package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ApplictionManager {

    WebDriver wd;
    private final Random random = new Random(); // no need in seed because by default it initialize using time

    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;

    private DataReader reader;
    private String browser;

    public ApplictionManager(String browser) {
        this.browser = browser;
    }

    public void init() {
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
        wd.get("http://localhost/addressbook/");
        getSessionHelper().login("admin", "secret");
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

    public DataReader reader() {
        return reader;
    }

    public Random getRandom() {
        return random;
    }
}
