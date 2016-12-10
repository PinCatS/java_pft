package ru.pincats.jpt.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by PinCatS on 10.12.2016.
 */
public class RegistrationHelper {
    private final ApplicationManager app;
    private final WebDriver wd;

    RegistrationHelper(ApplicationManager app){
        this.app = app;
        this.wd = app.getDriver();
    }

    public void start(String user, String email) {
        wd.get(app.properties().getProperty("web.baseUrl") + "/signup_page.php");
    }
}
