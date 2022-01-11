package com.tsi.training.gilliland.charlie.cocktailrecipes.cucumber;

import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.Glass;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testng.annotations.BeforeMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class deleteCocktailStep {
    @LocalServerPort
    private int port;

    Cocktail setUpCocktail = new Cocktail();
    Cocktail cocktail;
    int cocktailId;
    String name = "Tester";
    Instruction instruction = new Instruction();

    Response response;

    @Autowired
    CocktailRepository cocktailRepository;


    @Given("The cocktail exists in the database")
    public void the_cocktail_exists_in_the_database(){
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

    @Given("The cocktail does not exist in the database")
    public void the_cocktail_does_not_exist_in_the_database(){
    }

    @Given("I supply the correct id")
    public void i_supply_the_correct_id(){
        cocktailId = cocktail.getId();
    }

    @Given("I supply an incorrect id")
    public void i_supply_an_incorrect_id(){
        cocktailId = cocktail.getId() + 11;
    }

    @Given("I supply an id")
    public void i_supply_an_id(){
        cocktailId = anyInt();
    }

    @When("I submit a request to delete the cocktail")
    public void i_submit_a_request_to_delete_the_cocktail(){
        response = RestAssured.delete("http://localhost:"+ port +"/cocktail/deleteCocktail?id=" + cocktailId);
    }

    @Then("I receive a string to say the cocktail has been deleted")
    public void i_receive_a_string_to_say_the_cocktail_has_been_delete(){
        String expected = "Cocktail Deleted";
        String actual = response.getBody().asString();
        Assertions.assertEquals(expected, actual);
    }

    @Then("I receive an error from the server and the cocktail is not deleted")
    public void i_receive_an_error_from_the_server_and_the_cocktail_is_not_deleted(){
        Assertions.assertEquals(500, response.getStatusCode());
        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(cocktailId);
        Assertions.assertTrue(cocktailOptional.isEmpty());
    }





}
