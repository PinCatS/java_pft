package ru.pincats.jpt.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ApplicationManager {

    private WebDriver wd;

    private String browser;
    private Properties properties;
    private RegistrationHelper registrationHelper;

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
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public Properties properties() {
        return properties;
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }

        return registrationHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (Objects.equals(browser, BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (Objects.equals(browser, BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (Objects.equals(browser, BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }

            wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }

        return wd;
    }
}
