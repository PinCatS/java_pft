package ru.pincats.jpt.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
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

    public void loginPage() {
        if (isLoginPage()) return;
        click(By.cssSelector("a[href='my_view_page.php']"));
    }

    private boolean isLoginPage() {
        if (isElementPresent(By.id("login-form")))
            return true;
        return false;
    }

    public void gotoAddNewPage() {
        if (isNewPage()) return;
        click(By.linkText("add new"));
    }

    public void returnToHomePage() {
        if (isHomePage()) return;
        click(By.linkText("home page"));
    }

    public void usersManagement() {
        if (isUsersManagementPage()) return;
        click(By.linkText("Управление пользователями"));
    }

    private boolean isUsersManagementPage() {
        if (isElementPresent(By.cssSelector("div[@id='manage-user-div']")))
            return true;
        return false;
    }
}
