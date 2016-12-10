package ru.pincats.jpt.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by PinCatS on 11.12.2016.
 */
public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void finishReset(String confirmationLink, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }
}
