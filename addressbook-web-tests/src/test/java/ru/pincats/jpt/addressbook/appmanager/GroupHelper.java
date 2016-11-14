package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pincats.jpt.addressbook.model.GroupData;

import java.util.*;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class GroupHelper extends HelperBase {

    private ApplictionManager app;

    private Comparator<? super GroupData> comparatorById;

    public GroupHelper(WebDriver wd, ApplictionManager app) {
        super(wd);
        this.app = app;
        comparatorById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
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

    private void deleteGroup() {
        click(By.name("delete"));
    }

    public void selectById(int id) {
        getWd().findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForms(group);
        submitGroupCreation();
        app.goTo().backToGroupPage();
    }

    public void modify(GroupData group) {
        selectById(group.getId());
        initGroupModification();
        fillGroupForms(group);
        submitGroupModification();
        app.goTo().backToGroupPage();
    }

    public void delete(GroupData group) {
        app.group().selectById(group.getId());
        deleteGroup();
        app.goTo().backToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public Comparator<? super GroupData> getComparatorById() {
        return comparatorById;
    }

    public int getGroupNumber() {
        return getWd().findElements(By.name("selected[]")).size();
    }

    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<GroupData>();
        List<WebElement> elements = getWd().findElements(By.cssSelector("span.group"));
        for (WebElement e : elements) {
            String name = e.getText();
            String id = e.findElement(By.tagName("input")).getAttribute("value");
            groups.add(new GroupData().withId(Integer.parseInt(id)).withName(name));
        }
        return groups;
    }
}
