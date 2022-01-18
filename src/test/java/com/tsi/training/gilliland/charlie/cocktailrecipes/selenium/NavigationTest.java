package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.utility.Navigation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NavigationTest {
    WebDriver driver;
    Navigation nav;

    @Test
    public void testNavigationLinks(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        nav = new Navigation(driver);

        driver.get("http://localhost:3000/");

        nav.navigateToCreateCocktails();
        Assertions.assertEquals("http://localhost:3000/createCocktail", driver.getCurrentUrl());

        nav.navigateToCreateEquipment();
        Assertions.assertEquals("http://localhost:3000/createEquipment", driver.getCurrentUrl());

        nav.navigateToCreateIngredients();
        Assertions.assertEquals("http://localhost:3000/createIngredient", driver.getCurrentUrl());

        nav.navigateToCreateGarnish();
        Assertions.assertEquals("http://localhost:3000/createGarnish", driver.getCurrentUrl());

        nav.navigateToCreateGlass();
        Assertions.assertEquals("http://localhost:3000/createGlass", driver.getCurrentUrl());

        nav.navigateToViewCocktails();
        Assertions.assertEquals("http://localhost:3000/cocktails", driver.getCurrentUrl());

        nav.navigateToViewEquipment();
        Assertions.assertEquals("http://localhost:3000/equipment", driver.getCurrentUrl());

        nav.navigateToViewIngredients();
        Assertions.assertEquals("http://localhost:3000/ingredients", driver.getCurrentUrl());

        nav.navigateToViewGarnish();
        Assertions.assertEquals("http://localhost:3000/garnish", driver.getCurrentUrl());

        nav.navigateToViewGlass();
        Assertions.assertEquals("http://localhost:3000/glasses", driver.getCurrentUrl());

    }
}
