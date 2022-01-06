package com.tsi.training.gilliland.charlie.cocktailrecipes.cucumber;

import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class getAllCocktailsStep {
    @LocalServerPort
    private int port;

    @Autowired
    CocktailRepository cocktailRepository;

    Response response;
    String jsonResponse;

    @Given("there are cocktails stored in the database")
    public void there_are_cocktails_stored_in_the_database(){
        Assertions.assertFalse(cocktailRepository.findAll().isEmpty());
    }

    @When("I submit a request to get all cocktails")
    public void i_submit_a_request_to_get_all_cocktails(){
        response = RestAssured.get("http://localhost:"+ port +"/cocktail/getAll");
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Then("I should receive information on all the stored cocktails")
    public void i_should_receive_information_on_all_the_stored_cocktails(){
        jsonResponse = response.getBody().asString();
        Assertions.assertTrue(!jsonResponse.isEmpty());
        List<Cocktail> cocktailsInRepo = cocktailRepository.findAll();
        Cocktail[] cocktailsFromResponse = response.then().extract().as(Cocktail[].class);
        Assertions.assertEquals(cocktailsInRepo.size(), cocktailsFromResponse.length);
    }


}
