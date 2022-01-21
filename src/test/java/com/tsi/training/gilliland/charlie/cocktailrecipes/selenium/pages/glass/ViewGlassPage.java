package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.glass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewGlassPage {
    WebDriver driver;
    int addedGlassId = 0;

    By glasses = By.xpath("//table/tbody/tr");

    public ViewGlassPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean checkForEntry(String type, String volume){
        boolean match = false;
        List<WebElement> rows = driver.findElements(glasses);
        for(int i = 1; i <= rows.size(); i++){
            String rowType = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[2]")).getText();
            String rowVolume = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[3]")).getText();
            if(rowType.equals(type) && rowVolume.equals(volume)){
                match = true;
                addedGlassId = Integer.parseInt(driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText());
                break;
            }
        }
        return match;
    }

    public int getAddedGlassId() {
        return addedGlassId;
    }
}
