package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class CreateCocktailTest {

  @Test
  public void createCocktail() {
    WebDriver driver;
    System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("headless");
    options.addArguments("disable-gpu");
    driver = new ChromeDriver(options);
    driver.get("https://main.d3fprs5bjp2a56.amplifyapp.com/");
    driver.manage().window().setSize(new Dimension(1583, 1040));
    driver.findElement(By.linkText("Create")).click();
    driver.findElement(By.cssSelector(".nav-link:nth-child(1) > .darkText")).click();
    driver.findElement(By.id("cocktailNameInput")).click();
    driver.findElement(By.id("cocktailNameInput")).sendKeys("Tester");
    driver.findElement(By.id("cocktailDescriptionInput")).click();
    driver.findElement(By.id("cocktailDescriptionInput")).sendKeys("Tester");
    driver.findElement(By.cssSelector(".btn:nth-child(4)")).click();
    driver.close();
  }
}
