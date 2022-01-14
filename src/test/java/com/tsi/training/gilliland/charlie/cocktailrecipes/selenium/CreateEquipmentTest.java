package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CreateEquipmentTest {

    @Test
    public void createEquipment(){
        // Setting up the web driver
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Configuring the window
        driver.manage().window().maximize();

        // Getting the url of the website to test
        driver.get("http://localhost:3000/");

        // Creating variable for elements -- Home page
        driver.findElement(By.linkText("Create")).click();
        driver.findElement(By.linkText("Equipment")).click();


        // Creating variable for elements -- Create Equipment Page
        WebElement nameInput = driver.findElement(By.id("equipmentNameInput"));
        WebElement powerCheck = driver.findElement(By.id("equipmentPowerCheck"));
        WebElement createButton = driver.findElement(By.id("createEquipmentButton"));

        // Clicking the elements
        nameInput.click();
        nameInput.sendKeys("Selenium Equipment");
        powerCheck.click();
        createButton.click();

        // Making variables to assert
        String expectedUrl = "http://localhost:3000/equipment";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);

        driver.close();
    }

}
