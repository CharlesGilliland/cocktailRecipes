package com.tsi.training.gilliland.charlie.cocktailrecipes.selenium;

import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.GarnishService;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.garnish.CreateGarnishPage;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.garnish.ViewGarnishPage;
import com.tsi.training.gilliland.charlie.cocktailrecipes.selenium.pages.utility.Navigation;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateGarnishStep {
    WebDriver driver;
    Navigation navigation;
    CreateGarnishPage createGarnishPage;
    ViewGarnishPage viewGarnishPage;
    String type = "Selenium Type";
    String storage = "Selenium Storage";

    @Autowired
    GarnishService garnishService;

    @Given("I have navigated to the home page to create a garnish")
    public void i_have_navigated_to_the_home_page() {
        driver = CreateWebDriver.localDriver();
        navigation = new Navigation(driver);
        createGarnishPage = new CreateGarnishPage(driver);
        viewGarnishPage = new ViewGarnishPage(driver);
        driver.get("http://localhost:3000/");
    }

    @When("I click on the navigation link for the create garnish page")
    public void i_click_on_the_navigation_link_for_the_create_garnish_page() {
        navigation.navigateToCreateGarnish();
    }

    @Then("I am redirected to the create garnish page")
    public void i_am_redirected_to_the_create_garnish_page() {
        Assertions.assertEquals("http://localhost:3000/createGarnish", driver.getCurrentUrl());
    }

    @When("I enter the information for a new garnish")
    public void i_enter_the_information_for_a_new_garnish() {
        createGarnishPage.enterType(type);
        createGarnishPage.enterStorage(storage);
    }

    @When("I click on the create garnish button")
    public void i_click_on_the_create_garnish_button() {
        createGarnishPage.clickCreateButton();
    }

    @Then("I am redirected to the view garnish page")
    public void i_am_redirected_to_the_view_garnish_page() throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertEquals("http://localhost:3000/garnish", driver.getCurrentUrl());
    }

    @Then("I am able to see the newly created garnish")
    public void i_am_able_to_see_the_newly_created_garnish() throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertTrue(viewGarnishPage.checkForEntry(type, storage));
    }

    @After
    public void removeAddedGarnish() {
        // TODO write code to remove added element
    }




}
