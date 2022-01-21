package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.garnish;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewGarnishPage {
    WebDriver driver;
    int addedGarnishId = 0;

    By garnishes = By.xpath("//table/tbody/tr");

    public ViewGarnishPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean checkForEntry(String type, String storage){
        boolean match = false;
        List<WebElement> rows = driver.findElements(garnishes);
        for(int i = 1; i <= rows.size(); i++){
            String rowType = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[2]")).getText();
            String rowStorage = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[3]")).getText();
            if(rowType.equals(type) && rowStorage.equals(storage)){
                match = true;
                addedGarnishId = Integer.parseInt(driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText());
                break;
            }
        }
        return match;
    }

    public int getAddedGarnishId() {
        return addedGarnishId;
    }
}
