package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.equipment.CreateEquipmentPage;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.equipment.ViewEquipmentPage;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.utility.Navigation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CreateEquipmentTest {
    WebDriver driver;
    Navigation nav;
    CreateEquipmentPage createEquipmentPage;
    ViewEquipmentPage viewEquipmentPage;
    String name = "Selenium Name";

    @Test
    public void createEquipment() throws Exception {
        // Setting up the web driver and pages
        WebDriver driver;
        driver = CreateWebDriver.localDriver();
        nav = new Navigation(driver);
        createEquipmentPage = new CreateEquipmentPage(driver);
        viewEquipmentPage = new ViewEquipmentPage(driver);

        // Navigating to the correct page
        driver.get("http://localhost:3000/");
        nav.navigateToCreateEquipment();
        Assertions.assertEquals("http://localhost:3000/createEquipment", driver.getCurrentUrl());

        // Entering the info for the garnish
        createEquipmentPage.enterName(name);
        createEquipmentPage.checkPower();
        createEquipmentPage.clickCreateButton();

        // Checking the element is added to the list
        Thread.sleep(3000);
        driver.navigate().refresh();
        Thread.sleep(3000);
        Assertions.assertTrue(viewEquipmentPage.checkForEntry(name, "True"));
    }

}
