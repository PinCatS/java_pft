package ru.pincats.jpt.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PinCatS on 10.12.2016.
 */
public class RegistrationHelper extends HelperBase {

    RegistrationHelper(ApplicationManager app){
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.properties().getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }
}
