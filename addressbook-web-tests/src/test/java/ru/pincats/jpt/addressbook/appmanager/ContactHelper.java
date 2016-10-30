package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.pincats.jpt.addressbook.model.ContactData;

/**
 * Created by PinCatS on 30.10.2016.
 */
public class ContactHelper extends HelperBase{

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitAddNewForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillAddNewForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("lastname"),contactData.getLastName());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("title"),contactData.getTitle());
        type(By.name("company"),contactData.getCompany());
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("email"),contactData.getEmail());
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }
}
