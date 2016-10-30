package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class HelperBase {
    protected FirefoxDriver wd;

    public HelperBase(FirefoxDriver wd) {
        this.wd = wd;
    }

    protected void type(By locaror, String text) {
        click(locaror);
        wd.findElement(locaror).clear();
        wd.findElement(locaror).sendKeys(text);
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }


    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
