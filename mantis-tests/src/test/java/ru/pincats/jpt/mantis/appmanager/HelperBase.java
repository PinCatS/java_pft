package ru.pincats.jpt.mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class HelperBase {

    protected ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void type(By locator, String text) {
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!existingText.equals(text)) {
                WebElement we = wd.findElement(locator);
                we.clear();
                we.sendKeys(text);
            }
        }
    }

    protected void attach(By locator, String absolutePath) {
        WebElement we = wd.findElement(locator);
        we.sendKeys(absolutePath);
    }

    protected void click(By locator) {
        try {
            wd.findElement(locator).click();
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException detected");
            wd.findElement(locator).click(); // retry
        }

    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    protected void select(By locator, String text) {
        if (text != null) {
            new Select(wd.findElement(locator)).selectByVisibleText(text);
        }
    }

    protected void selectByValue(By locator, String text) {
        if (text != null) {
            new Select(wd.findElement(locator)).selectByValue(text);
        }
    }


    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected WebDriver getWd() {
        return wd;
    }

    public void login(String username, String password) {
        app.goTo().loginPage();
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));
    }
}
