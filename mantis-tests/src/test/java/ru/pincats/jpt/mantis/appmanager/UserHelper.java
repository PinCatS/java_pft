package ru.pincats.jpt.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by PinCatS on 11.12.2016.
 */
public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        app.goTo().loginPage();
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));
    }

    public void finishReset(String confirmationLink, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }
}
