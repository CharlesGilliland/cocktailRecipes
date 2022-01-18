package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Navigation {
    WebDriver driver;
    By createDropdown = By.linkText("Create");
    By viewDropdown = By.linkText("View");
    By cocktails = By.linkText("Cocktails");
    By equipment = By.linkText("Equipment");
    By ingredients = By.linkText("Ingredients");
    By garnish = By.linkText("Garnish");
    By glass = By.linkText("Glass");

    public Navigation(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToCreateCocktails(){
        driver.findElement(createDropdown).click();
        driver.findElement(cocktails).click();
    }

    public void navigateToCreateEquipment(){
        driver.findElement(createDropdown).click();
        driver.findElement(equipment).click();
    }

    public void navigateToCreateIngredients(){
        driver.findElement(createDropdown).click();
        driver.findElement(ingredients).click();
    }

    public void navigateToCreateGarnish(){
        driver.findElement(createDropdown).click();
        driver.findElement(garnish).click();
    }

    public void navigateToCreateGlass(){
        driver.findElement(createDropdown).click();
        driver.findElement(glass).click();
    }

    public void navigateToViewCocktails(){
        driver.findElement(viewDropdown).click();
        driver.findElement(cocktails).click();
    }

    public void navigateToViewEquipment(){
        driver.findElement(viewDropdown).click();
        driver.findElement(equipment).click();
    }

    public void navigateToViewIngredients(){
        driver.findElement(viewDropdown).click();
        driver.findElement(ingredients).click();
    }

    public void navigateToViewGarnish(){
        driver.findElement(viewDropdown).click();
        driver.findElement(garnish).click();
    }

    public void navigateToViewGlass(){
        driver.findElement(viewDropdown).click();
        driver.findElement(glass).click();
    }
}
