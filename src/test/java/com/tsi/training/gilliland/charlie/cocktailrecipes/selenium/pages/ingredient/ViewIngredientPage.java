package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.ingredient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewIngredientPage {
    WebDriver driver;

    By ingredients = By.xpath("//table/tbody/tr");

    public ViewIngredientPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean checkForEntry(String name, String type, String abv, String storage, String description){
        boolean match = false;
        List<WebElement> rows = driver.findElements(ingredients);
        for(int i = 1; i < rows.size(); i++){
            String rowName = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[2]")).getText();
            String rowType = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[3]")).getText();
            String rowAbv = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[4]")).getText();
            String rowStorage = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[5]")).getText();
            String rowDescription = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[6]")).getText();
            if(rowName.equals(name) && rowType.equals(type) && rowAbv.equals(abv) && rowStorage.equals(storage) && rowDescription.equals(description)){
                match = true;
            }
        }
        return match;
    }
}
