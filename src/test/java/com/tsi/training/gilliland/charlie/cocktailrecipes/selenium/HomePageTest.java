package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.utility.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HomePageTest {
    HomePage homePage;
    WebDriver driver;

    @Test
    public void homeGetStartedTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");
        homePage.clickGetStarted();
        Assertions.assertEquals("http://localhost:3000/cocktails", driver.getCurrentUrl());
        driver.close();
    }
}
