package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.ingredient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateIngredientPage {
    WebDriver driver;

    By nameInput = By.id("ingredientNameInput");
    By typeInput = By.id("ingredientTypeInput");
    By abvInput = By.id("ingredientAbvInput");
    By storageInput = By.id("ingredientStorageInput");
    By descriptionInput = By.id("ingredientDescriptionInput");
    By createButton = By.id("createIngredientButton");

    public CreateIngredientPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterName(String name){
        driver.findElement(nameInput).sendKeys(name);
    }

    public void enterType(String type){
        driver.findElement(typeInput).sendKeys(type);
    }

    public void enterAbv(String abv){
        driver.findElement(abvInput).sendKeys(abv);
    }

    public void enterStorage(String storage){
        driver.findElement(storageInput).sendKeys(storage);
    }

    public void enterDescription(String description){
        driver.findElement(descriptionInput).sendKeys(description);
    }

    public void clickCreateButton(){
        driver.findElement(createButton).click();
    }
}
