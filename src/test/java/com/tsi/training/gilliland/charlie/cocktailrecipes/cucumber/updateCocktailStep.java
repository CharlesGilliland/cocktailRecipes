package com.tsi.training.gilliland.charlie.cocktailrecipes.cucumber;

import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class updateCocktailStep {
    @LocalServerPort
    private int port;

    Cocktail setUpCocktail = new Cocktail();
    Cocktail cocktail;
    String name = "Tester";
    String updatedName = "Updated Tester";
    Instruction instruction = new Instruction();
    RequestSpecification request;
    Response response;

    @Autowired
    CocktailRepository cocktailRepository;

    @Given("The cocktail already exists in the database")
    public void the_cocktail_already_exists_in_the_database(){
        setUpCocktail.setName(name);
        setUpCocktail.addInstruction(instruction);
        cocktail = RestAssured.given()
                .header("Content-Type","application/json")
                .body(setUpCocktail)
                .post("http://localhost:"+ port +"/cocktail/addCocktail")
                .then()
                .extract()
                .as(Cocktail.class);
        Assertions.assertTrue(cocktailRepository.findById(cocktail.getId()).isPresent());
    }

    @Given("The cocktail to update does not exist in the database")
    public void the_cocktail_to_update_does_not_exist_in_the_database(){
        cocktail = new Cocktail();
    }

    @Given("I supply an updated version of the cocktail")
    public void i_supply_an_updated_version_of_the_cocktail(){
        cocktail.setName(updatedName);
    }

    @When("I submit a request to update the cocktail")
    public void i_submit_a_request_to_update_the_cocktail(){
        request = RestAssured.given().header("Content-Type","application/json").body(cocktail);
        response = request.put("http://localhost:"+ port +"/cocktail/updateCocktail");
    }

    @Then("I receive a string to say the cocktail has been updated")
    public void i_receive_a_string_to_say_the_cocktail_has_been_updated(){
        String expected = "Cocktail Updated";
        String actual = response.getBody().asString();
        Assertions.assertEquals(expected, actual);
    }

    @Then("I receive an error from the server and the cocktail is not updated")
    public void i_receive_an_error_from_the_server_and_the_cocktail_is_not_updated(){
        Assertions.assertEquals(500, response.getStatusCode());
    }
}
