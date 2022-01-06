package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

// Generated by Selenium IDE

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
public class TestViewListsTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void testViewLists() {
    driver.get("http://localhost:3000/");
    driver.manage().window().setSize(new Dimension(1294, 1040));
    driver.findElement(By.linkText("View")).click();
    driver.findElement(By.linkText("Cocktails")).click();
    driver.findElement(By.linkText("View")).click();
    driver.findElement(By.linkText("Equipment")).click();
    driver.findElement(By.linkText("View")).click();
    driver.findElement(By.linkText("Ingredients")).click();
    driver.findElement(By.linkText("View")).click();
    driver.findElement(By.linkText("Garnish")).click();
    driver.findElement(By.linkText("View")).click();
    driver.findElement(By.linkText("Glass")).click();
    driver.close();
  }
}
