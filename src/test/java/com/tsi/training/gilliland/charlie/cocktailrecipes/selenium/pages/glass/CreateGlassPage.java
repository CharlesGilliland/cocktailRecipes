package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.glass;

import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.garnish.CreateGarnishPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateGlassPage {
    WebDriver driver;

    By typeInput = By.id("glassTypeInput");
    By volumeInput = By.id("glassVolumeInput");
    By createButton = By.id("createGlassButton");

    public CreateGlassPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterType(String type){
        driver.findElement(typeInput).sendKeys(type);
    }

    public void enterVolume(String volume){
        driver.findElement(volumeInput).sendKeys(volume);
    }

    public void clickCreateButton(){
        driver.findElement(createButton).click();
    }
}
