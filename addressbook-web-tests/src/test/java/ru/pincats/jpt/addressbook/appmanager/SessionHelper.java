package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class SessionHelper extends HelperBase {

    private ApplictionManager app;

    public SessionHelper(WebDriver wd, ApplictionManager app) {
        super(wd);
        this.app = app;
    }

    public void login(String login, String password) {
        type(By.name("user"), login);
        type(By.name("pass"), password);
        click(By.xpath("//form[@id='LoginForm']/input[@value='Login']"));
    }
}
