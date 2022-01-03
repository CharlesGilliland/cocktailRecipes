package com.tsi.training.gilliland.charlie.cocktailRecipes.cucumber;

import com.google.gson.Gson;
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.CocktailRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class addCocktailStepDefs {
    @Autowired
    CocktailRepository cocktailRepository;

    Gson gson = new Gson();

    Cocktail cocktail;
    Instruction instruction = new Instruction();
    String name = "Tester";
    Response response;

    @Given("I have supplied a name")
    public void i_have_supplied_a_name(){
        cocktail = new Cocktail();
        cocktail.setName(name);
    }

    @Given("I have supplied a set of instructions")
    public void i_have_supplied_a_set_of_instructions(){
        cocktail.addInstruction(instruction);
    }

    @Given("I have supplied a description")
    public void i_have_supplied_a_description(){
        cocktail.setDescription("Test description");
    }

    @When("I submit a request to add the cocktail")
    public void i_submit_a_request_to_add_the_cocktail(){
        response = RestAssured.given().header("Content-Type","application/json").body(cocktail).post("http://localhost:8080/cocktail/addCocktail");
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Then("the cocktail is stored in the database")
    public void the_cocktail_should_be_stored_in_the_database(){

    }

    @Then("I receive a message to say it has been saved")
    public void i_receive_a_message_to_say_it_has_been_saved(){

    }

    @After
    public void removeCocktailFromDb(){
//        cocktailRepository.deleteById(cocktail.getId());
    }

}
