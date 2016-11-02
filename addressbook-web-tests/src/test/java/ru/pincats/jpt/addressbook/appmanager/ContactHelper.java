package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.pincats.jpt.addressbook.model.ContactData;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
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

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']//input[@value='Update']"));
    }

    public void acceptContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        fillContactForm(contact, true);
        submitAddNewForm();
    }
}
