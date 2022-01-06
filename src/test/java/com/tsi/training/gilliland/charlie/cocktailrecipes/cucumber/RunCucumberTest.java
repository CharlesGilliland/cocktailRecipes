package com.tsi.training.gilliland.charlie.cocktailrecipes.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty", "json:target/cucumber.json", "html:target/cucumber"},
                features={"src/test/resources"})
public class RunCucumberTest{
}
