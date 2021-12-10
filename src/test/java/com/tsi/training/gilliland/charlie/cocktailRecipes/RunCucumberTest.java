package com.tsi.training.gilliland.charlie.cocktailRecipes;

// New Imports :)
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

// Old Imports :(
//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty"},
                features={"src/test/resources/cucumber"},
                glue={"com/tsi/training/gilliland/charlie/cocktailRecipes"})
public class RunCucumberTest {
}
