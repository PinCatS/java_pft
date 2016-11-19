package ru.pincats.jpt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.pincats.jpt.addressbook.model.ContactData;
import ru.pincats.jpt.addressbook.model.Contacts;

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
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("phone2"),contactData.getHomePhone2());
        type(By.name("work"),contactData.getWorkPhone());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmail2());
        type(By.name("email3"),contactData.getEmail3());
        type(By.name("address"),contactData.getPostAddress());
        attach(By.name("photo"), contactData.getPhoto().getAbsolutePath());

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

    public void selectById(int id) {
        getWd().findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void initContactModificationById(int id) {
        getWd().findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void gotoContactViewFormById(int id) {
        getWd().findElement(By.cssSelector("a[href='view.php?id=" + id + "']")).click();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String lastName = getWd().findElement(By.name("lastname")).getAttribute("value");
        String firstName = getWd().findElement(By.name("firstname")).getAttribute("value");
        String nickname = getWd().findElement(By.name("nickname")).getAttribute("value");
        String company = getWd().findElement(By.name("company")).getAttribute("value");
        String title = getWd().findElement(By.name("title")).getAttribute("value");
        String email = getWd().findElement(By.name("email")).getAttribute("value");
        String email2 = getWd().findElement(By.name("email2")).getAttribute("value");
        String email3 = getWd().findElement(By.name("email3")).getAttribute("value");
        String postAddress = getWd().findElement(By.name("address")).getText();
        String homePhone = getWd().findElement(By.name("home")).getAttribute("value");
        String mobilePhone = getWd().findElement(By.name("mobile")).getAttribute("value");
        String workPhone = getWd().findElement(By.name("work")).getAttribute("value");
        String homePhone2 = getWd().findElement(By.name("phone2")).getAttribute("value");

        app.goTo().homePage();

        return new ContactData().withId(contact.getId())
                .withLastName(lastName).withFirstName(firstName).withNickname(nickname)
                .withCompany(company).withTitle(title)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withHomePhone2(homePhone2)
                .withPostAddress(postAddress);
    }

    public ContactData infoFromDetailsForm(ContactData contact) {
        gotoContactViewFormById(contact.getId());
        String content = getWd().findElement(By.id("content")).getText()
                .replaceAll("[-()]", "").replaceAll("[MWHP]\\:", "")
                .replaceAll("Member of[:]\\s+[\\w]+","") // for simplicity not gonna compare list of groups
                .replaceAll(" +"," ").replaceAll("\\n+\\s*", "\n").replaceAll("\\n$", "")
                .replaceFirst(" ", "\n");   // finally, split First and Last names

        app.goTo().homePage();

        return new ContactData().withAllDetails(content);
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']//input[@value='Update']"));
    }

    public void acceptContactDeletion() {
        getWd().switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        app.goTo().gotoAddNewPage();
        fillContactForm(contact, true);
        submitAddNewForm();
        contactsCache = null;
        app.goTo().returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactsCache = null;
        app.goTo().returnToHomePage();
    }

    public void delete(ContactData deletedData) {
        app.contact().selectById(deletedData.getId());
        app.contact().deleteContact();
        app.contact().acceptContactDeletion();
        contactsCache = null;
        app.goTo().homePage();
    }

    public Comparator<? super ContactData> getComparatorById() {
        return comparatorById;
    }

    public int count() {
        return getWd().findElements(By.name("selected[]")).size();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private ContactData extarctContactFromRow(List<WebElement> fields) {
        String lastName = fields.get(1).getText();
        String firstName = fields.get(2).getText();
        String postAddress = fields.get(3).getText();
        String allEmails = fields.get(4).getText();
        String allPhones = fields.get(5).getText();
        String id = fields.get(0).findElement(By.tagName("input")).getAttribute("id");

        return new ContactData()
                .withId(Integer.parseInt(id))
                .withFirstName(firstName).withLastName(lastName)
                .withAllEmails(allEmails).withAllPhones(allPhones)
                .withPostAddress(postAddress);
    }

    private Contacts contactsCache = null;

    public Contacts all() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }

        contactsCache = new Contacts();
        List<WebElement> elements = getWd().findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement e : elements) {
            ContactData contact = extarctContactFromRow(e.findElements(By.tagName("td")));
            contactsCache.add(contact);
        }
        return new Contacts(contactsCache);
    }
}
