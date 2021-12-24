package com.tsi.training.gilliland.charlie.cocktailRecipes.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty"},
                features={"src/test/resources/cucumber"},
                glue={"com/tsi/training/gilliland/charlie/cocktailRecipes/cucumber"})
public class RunCucumberTest {
}
