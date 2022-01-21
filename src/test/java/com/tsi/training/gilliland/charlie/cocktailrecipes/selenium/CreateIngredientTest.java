package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.ingredient.CreateIngredientPage;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.ingredient.ViewIngredientPage;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.utility.Navigation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateIngredientTest {
    WebDriver driver;
    Navigation nav;
    CreateIngredientPage createIngredientPage;
    ViewIngredientPage viewIngredientPage;
    String name = "Selenium Name";
    String type = "Selenium Type";
    String abv = "37.5";
    String storage = "Selenium Storage";
    String description = "Selenium Description";

    @Test
    public void createIngredient() throws InterruptedException {
        // Setting up the web driver and pages
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        nav = new Navigation(driver);
        createIngredientPage = new CreateIngredientPage(driver);
        viewIngredientPage = new ViewIngredientPage(driver);

        // Navigating to the correct page
        driver.get("http://localhost:3000/");
        nav.navigateToCreateIngredients();
        Assertions.assertEquals("http://localhost:3000/createIngredient", driver.getCurrentUrl());

        // Entering the info
        createIngredientPage.enterName(name);
        createIngredientPage.enterType(type);
        createIngredientPage.enterAbv(abv);
        createIngredientPage.enterStorage(storage);
        createIngredientPage.enterDescription(description);
        createIngredientPage.clickCreateButton();

        Thread.sleep(3000);
        driver.navigate().refresh();
        Thread.sleep(3000);
        Assertions.assertTrue(viewIngredientPage.checkForEntry(name, type, abv, storage, description));
        driver.close();
    }
}
