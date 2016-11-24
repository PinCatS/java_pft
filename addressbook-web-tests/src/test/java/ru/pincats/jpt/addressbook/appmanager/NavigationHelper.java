package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class NavigationHelper extends HelperBase {

    private ApplicationManager app;

    public NavigationHelper(WebDriver wd, ApplicationManager app) {
        super(wd);
        this.app = app;
    }

    private boolean isGroupPage() {
        if (isElementPresent(By.name("h1"))
                && getWd().findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return true;
        }
        return false;
    }

    private boolean isHomePage() {
        if (isElementPresent(By.id("maintable")))
            return true;
        return false;
    }

    private boolean isNewPage() {
        if (isElementPresent(By.name("h1"))
                && getWd().findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
                && isElementPresent(By.name("submit"))) {
            return true;
        }
        return false;
    }

    public void backToGroupPage() {
        if (isGroupPage()) return;
        click(By.linkText("group page"));
    }

    public void groupPage() {
        if (isGroupPage()) return;
        click(By.linkText("groups"));
    }

    public void homePage() {
        if (isHomePage()) return;
        click(By.linkText("home"));
    }

    public void gotoAddNewPage() {
        if (isNewPage()) return;
        click(By.linkText("add new"));
    }

    public void returnToHomePage() {
        if (isHomePage()) return;
        click(By.linkText("home page"));
    }
}
