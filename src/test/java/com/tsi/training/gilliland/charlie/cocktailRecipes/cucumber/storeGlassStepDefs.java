package com.tsi.training.gilliland.charlie.cocktailRecipes.cucumber;

import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.CocktailService;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.Glass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@CucumberContextConfiguration
@SpringBootTest
public class storeGlassStepDefs {

    @Autowired
    CocktailService cocktailService;

    private static HttpURLConnection connection;

    StringBuilder result = new StringBuilder();

    @Given("I am successfully connected to the API")
    public void i_am_successfully_connected_to_the_API() throws Exception{
        URL url = new URL("http://localhost:8080/glass/getAll");
        connection = (HttpURLConnection) url.openConnection();

    }


    @When("I submit a request to get all glasses")
    public void i_submit_a_request_to_get_all_glasses() throws Exception {
        connection.setRequestMethod("GET");

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        )) {
            for(String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
    }

    @Then("All the glasses should be returned")
    public void the_glass_should_be_stored() {
        Assertions.assertEquals(cocktailService.getAll().toString(), result.toString());
    }


}