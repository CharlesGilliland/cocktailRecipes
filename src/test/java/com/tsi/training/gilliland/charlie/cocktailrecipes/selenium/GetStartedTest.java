package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.utility.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetStartedTest {

    WebDriver driver;
    HomePage homePage;

    @BeforeMethod
    public void setUp(){
        driver = CreateWebDriver.localDriver();
        driver.get("http://localhost:3000/");
    }

    @Test
    public void getStarted(){
        homePage = new HomePage(driver);
        homePage.clickGetStarted();
        driver.close();
    }

}
