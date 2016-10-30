package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class SessionHelper extends HelperBase {

    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void login(String login, String password) {
        type(By.name("user"), login);
        type(By.name("pass"), password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
