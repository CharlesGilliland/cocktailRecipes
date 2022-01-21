package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.garnish.CreateGarnishPage;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.utility.Navigation;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.garnish.ViewGarnishPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateGarnishTest {
    Navigation nav;
    CreateGarnishPage createGarnishPage;
    ViewGarnishPage viewGarnishPage;
    WebDriver driver;
    String type = "Selenium Type";
    String storage = "Selenium Storage";

    @Test
    public void createGarnish() throws InterruptedException {
        // Setting up the web driver and pages
        WebDriver driver;
        driver = CreateWebDriver.localDriver();
        nav = new Navigation(driver);
        createGarnishPage = new CreateGarnishPage(driver);
        viewGarnishPage = new ViewGarnishPage(driver);

        // Navigating to the correct page
        driver.get("http://localhost:3000/");
        nav.navigateToCreateGarnish();
        Assertions.assertEquals("http://localhost:3000/createGarnish", driver.getCurrentUrl());

        // Entering the info for the garnish
        createGarnishPage.enterType(type);
        createGarnishPage.enterStorage(storage);
        createGarnishPage.clickCreateButton();

        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        Assertions.assertTrue(viewGarnishPage.checkForEntry(type, storage));
        driver.close();
    }
}
