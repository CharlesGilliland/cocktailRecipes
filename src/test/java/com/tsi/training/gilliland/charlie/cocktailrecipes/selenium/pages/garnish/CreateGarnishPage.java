package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.garnish;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateGarnishPage {
    WebDriver driver;

    By typeInput = By.id("garnishTypeInput");
    By storageInput = By.id("garnishStorageInput");
    By createButton = By.id("createGarnishButton");

    public CreateGarnishPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterType(String type){
        driver.findElement(typeInput).sendKeys(type);
    }

    public void enterStorage(String storage){
        driver.findElement(storageInput).sendKeys(storage);
    }

    public void clickCreateButton(){
        driver.findElement(createButton).click();
    }
}
