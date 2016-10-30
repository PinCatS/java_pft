package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void gotoGroupPage() {
        wd.findElement(By.id("content")).click();
        wd.findElement(By.linkText("groups")).click();
    }

    public void gotoHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    public void gotoAddNewPage() {
        wd.findElement(By.linkText("add new")).click();
    }
}
