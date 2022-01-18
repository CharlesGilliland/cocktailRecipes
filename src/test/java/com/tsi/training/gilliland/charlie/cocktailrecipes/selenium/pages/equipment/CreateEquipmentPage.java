package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.equipment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CreateEquipmentPage {
    WebDriver driver;

    By nameInput = By.id("equipmentNameInput");
    By powerCheck = By.id("equipmentPowerCheck");
    By createButton = By.id("createEquipmentButton");

    public CreateEquipmentPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterName(String name){
        driver.findElement(nameInput).sendKeys(name);
    }

    public void checkPower(){
        driver.findElement(powerCheck).click();
    }

    public void clickCreateButton(){
        driver.findElement(createButton).click();
    }
}
