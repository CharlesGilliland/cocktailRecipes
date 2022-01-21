package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.GlassService;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.glass.CreateGlassPage;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.glass.ViewGlassPage;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.utility.Navigation;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;


public class CreateGlassStep {
    WebDriver driver = CreateWebDriver.localDriver();
    Navigation navigation = new Navigation(driver);
    CreateGlassPage createGlassPage = new CreateGlassPage(driver);
    ViewGlassPage viewGlassPage = new ViewGlassPage(driver);
    String type = "Selenium Type";
    String volume = "50";

    @Autowired
    GlassService glassService;

    @Given("I have navigated to the home page to create a glass")
    public void i_have_navigated_to_the_home_page() {
        driver.get("http://localhost:3000/");
    }

    @When("I click on the navigation link for the create glass page")
    public void i_click_on_the_navigation_link_for_the_create_glass_page() {
        navigation.navigateToCreateGlass();
    }

    @Then("I am redirected to the create glass page")
    public void i_am_redirected_to_the_create_glass_page() {
        Assertions.assertEquals("http://localhost:3000/createGlass", driver.getCurrentUrl());
    }

    @When("I enter the information for a new glass")
    public void i_enter_the_information_for_a_new_glass() {
        createGlassPage.enterType(type);
        createGlassPage.enterVolume(volume);
    }

    @When("I click on the create glass button")
    public void i_click_on_the_create_glass_button() {
        createGlassPage.clickCreateButton();
    }

    @Then("I am redirected to the view glass page")
    public void i_am_redirect_to_the_view_glass_page() throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertEquals("http://localhost:3000/glasses", driver.getCurrentUrl());
    }

    @Then("I am able to see the newly created glass")
    public void i_am_able_to_see_the_newly_created_glass() throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertTrue(viewGlassPage.checkForEntry(type, volume));
    }

    @After
    public void removeAddedGlass(){
        // TODO write code to remove added element
    }
}
