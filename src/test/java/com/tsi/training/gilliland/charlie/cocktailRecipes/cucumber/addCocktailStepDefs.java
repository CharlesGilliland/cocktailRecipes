package com.tsi.training.gilliland.charlie.cocktailRecipes.cucumber;

import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.CocktailRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class addCocktailStepDefs {
    @Autowired
    CocktailRepository cocktailRepository;

    Cocktail cocktail = new Cocktail();
    Instruction instruction = new Instruction();
    String name = "Tester";
    String description = "Test description";
    RequestSpecification request;
    Response response;
    JsonPath json;

    @Given("I have supplied a name")
    public void i_have_supplied_a_name(){
        cocktail.setName(name);
    }

    @Given("I have not supplied a name")
    public void i_have_not_supplied_a_name(){
        cocktail.setName(null);
    }

    @Given("I have supplied a set of instructions")
    public void i_have_supplied_a_set_of_instructions(){
        cocktail.addInstruction(instruction);
    }

    @Given("I have not supplied a set of instructions")
    public void i_have_not_supplied_a_set_of_instructions(){

    }

    @Given("I have supplied a description")
    public void i_have_supplied_a_description(){
        cocktail.setDescription(description);
    }

    @Given("I have not supplied a description")
    public void i_have_not_supplied_a_description(){
        cocktail.setDescription(null);
    }

    @When("I submit a request to add the cocktail")
    public void i_submit_a_request_to_add_the_cocktail(){
        request = RestAssured.given().header("Content-Type","application/json").body(cocktail);
        response = request.post("http://localhost:8080/cocktail/addCocktail");
    }

    @Then("I receive the json of the saved cocktail")
    public void i_receive_the_json_of_the_saved_cocktail(){
        Assertions.assertEquals(200, response.getStatusCode());
        json = response.jsonPath();
        Cocktail cocktailFromJson = response.then().extract().as(Cocktail.class);
        Assertions.assertEquals(cocktail.getName(), cocktailFromJson.getName());
        Assertions.assertEquals(cocktail.getDescription(), cocktailFromJson.getDescription());

        // Deleting the cocktail so it's not persisted
        int id = json.get("id");
        cocktailRepository.deleteById(id);
    }

    @Then("I should receive an error from the server")
    public void i_should_receive_an_error_from_the_server(){
        Assertions.assertEquals(500, response.getStatusCode());
    }



}
