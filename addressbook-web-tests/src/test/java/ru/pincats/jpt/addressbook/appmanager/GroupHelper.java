package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.pincats.jpt.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class GroupHelper extends HelperBase {

    ApplictionManager app;

    public GroupHelper(WebDriver wd, ApplictionManager app) {
        super(wd);
        this.app = app;
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForms(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void selectGroup(int index) {
        getWd().findElements(By.name("selected[]")).get(index).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForms(group);
        submitGroupCreation();
        app.getNavigationHelper().returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupNumber() {
        return getWd().findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = getWd().findElements(By.cssSelector("span.group"));
        for (WebElement e : elements) {
            String name = e.getText();
            groups.add(new GroupData(name, null, null));
        }
        return groups;
    }
}
