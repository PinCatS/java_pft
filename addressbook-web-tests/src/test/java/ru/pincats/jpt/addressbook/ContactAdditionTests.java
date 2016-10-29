package ru.pincats.jpt.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class ContactAdditionTests extends TestBase {

    @Test
    public void testContactAddition() {
        gotoAddNewPage();
        fillAddNewForm(new ContactData("Sergey", "Li", "pincats", "Principal Software Engineer", "DELL EMC", "+7 921 312 08 69", "pincats@gmail.com"));
        submitAddNewForm();
        gotoHomePage();
    }

}
