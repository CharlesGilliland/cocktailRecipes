package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;
    By getStartedButton = By.id("getStartedButton");


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickGetStarted() {
        driver.findElement(getStartedButton).click();
    }
}
