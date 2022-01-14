package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;
    By getStartedButton = By.id("getStartedButton");
    By createDropdown = By.linkText("Create");
    By viewDropdown = By.linkText("View");
    By viewCocktails = By.linkText("Cocktails");
    By viewEquipment = By.linkText("Equipment");
    By viewIngredients = By.linkText("Ingredients");
    By viewGarnish = By.linkText("Garnish");
    By viewGlass = By.linkText("Glass");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickGetStarted() {
        driver.findElement(getStartedButton).click();
    }
}
