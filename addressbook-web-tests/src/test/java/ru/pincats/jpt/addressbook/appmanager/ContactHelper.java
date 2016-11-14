package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.pincats.jpt.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ContactHelper extends HelperBase{

    private ApplictionManager app;

    private Comparator<? super ContactData> comparatorById;

    public ContactHelper(WebDriver wd, ApplictionManager app) {
        super(wd);
        this.app = app;
        comparatorById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    }

    public void submitAddNewForm() {
        click(By.xpath("//div[@id='content']//input[@value='Enter']"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("lastname"),contactData.getLastName());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("title"),contactData.getTitle());
        type(By.name("company"),contactData.getCompany());
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("email"),contactData.getEmail());

        if (creation) {
            if (!isElementPresent(By.xpath("//select[@name='new_group']//option[text()='" + contactData.getGroup() + "']"))) {
                select(By.name("new_group"), "[none]"); // at least should be [none] value
            }
            else {
                select(By.name("new_group"), contactData.getGroup());
            }
        } else {
            Assert.assertEquals(isElementPresent(By.name("new_group")), false);
        }
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']//input[@value='Delete']"));
    }

    public void selectContact(int index) {
        getWd().findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification(int index) {
        index += 2; // difference between selected[] and tr indexing
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + index + "]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']//input[@value='Update']"));
    }

    public void acceptContactDeletion() {
        getWd().switchTo().alert().accept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        app.getNavigationHelper().gotoAddNewPage();
        fillContactForm(contact, true);
        submitAddNewForm();
        app.getNavigationHelper().returnToHomePage();
    }

    public void modifyContact(int index, ContactData contact) {
        initContactModification(index);
        fillContactForm(contact, false);
        submitContactModification();
        app.getNavigationHelper().returnToHomePage();
    }

    public Comparator<? super ContactData> getComparatorById() {
        return comparatorById;
    }

    public int getContactsNumber() {
        return getWd().findElements(By.name("selected[]")).size();
    }

    private ContactData extarctContactFromRow(List<WebElement> fields) {
        String last_name = fields.get(1).getText();
        String first_name = fields.get(2).getText();
        String email = fields.get(4).getText();
        String mobile = fields.get(5).getText();
        String href_with_id = fields.get(6).findElement(By.cssSelector("a")).getAttribute("href");
        int id = Integer.parseInt(href_with_id.substring(href_with_id.indexOf("id=")+3));
        return new ContactData(id, first_name, last_name, null, null, null, mobile, email, null);
    }

    public List<ContactData> getContactsList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = getWd().findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement e : elements) {
            ContactData contact = extarctContactFromRow(e.findElements(By.tagName("td")));
            contacts.add(contact);
        }
        return contacts;
    }
}
