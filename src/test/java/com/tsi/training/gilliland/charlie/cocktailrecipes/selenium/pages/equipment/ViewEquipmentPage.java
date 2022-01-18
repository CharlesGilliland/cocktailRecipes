package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.equipment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewEquipmentPage {
    WebDriver driver;

    By equipment = By.xpath("//table/tbody/tr");

    public ViewEquipmentPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean checkForEntry(String name, String isPowered){
        boolean match = false;
        List<WebElement> rows = driver.findElements(equipment);
        for(int i = 1; i <= rows.size(); i++){
            String rowType = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[2]")).getText();
            String rowVolume = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[3]")).getText();
            if(rowType.equals(name) && rowVolume.equals(isPowered)){
                match = true;
            }
        }
        return match;
    }
}
