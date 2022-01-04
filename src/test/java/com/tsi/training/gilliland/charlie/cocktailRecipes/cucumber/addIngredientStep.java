package com.tsi.training.gilliland.charlie.cocktailRecipes.cucumber;

import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.Ingredient;
import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.IngredientRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class addIngredientStep {
    Ingredient ingredient = new Ingredient();
    String name = "Test";
    String type = "Test";
    float abv = 5;
    String storage = "Test";
    String description = "Test";
    RequestSpecification request;
    Response response;

    @Autowired
    IngredientRepository ingredientRepository;

    @Given("I have supplied a name for the ingredient")
    public void i_have_supplied_a_name_for_the_ingredient(){
        ingredient.setName(name);
    }

    @Given("I have not supplied a name for the ingredient")
    public void i_have_not_supplied_a_name_for_the_ingredient(){
    }

    @Given("I have supplied a type for the ingredient")
    public void i_have_supplied_a_type_for_the_ingredient(){
        ingredient.setType(type);
    }

    @Given("I have not supplied a type for the ingredient")
    public void i_have_not_supplied_a_type_for_the_ingredient(){
    }

    @Given("I have supplied an abv for the ingredient")
    public void i_have_supplied_an_abv_for_the_ingredient(){
        ingredient.setAbv(abv);
    }

    @Given("I have not supplied an abv for the ingredient")
    public void i_have_not_supplied_an_abv_for_the_ingredient(){
    }
    @Given("I have supplied an incorrect abv for the ingredient")
    public void i_have_supplied_an_incorrect_abv_for_the_ingredient(){
        ingredient.setAbv(120);
    }

    @Given("I have supplied a storage for the ingredient")
    public void i_have_supplied_a_storage_for_the_ingredient(){
        ingredient.setStorage(storage);
    }

    @Given("I have not supplied a storage for the ingredient")
    public void i_have_not_supplied_a_storage_for_the_ingredient(){
    }

    @Given("I have supplied a description for the ingredient")
    public void i_have_supplied_a_description_for_the_ingredient(){
        ingredient.setDescription(description);
    }

    @Given("I have not supplied a description for the ingredient")
    public void i_have_not_supplied_a_description_for_the_ingredient(){
    }

    @When("I submit a request to add the ingredient")
    public void i_submit_a_request_to_add_the_ingredient(){
        request = RestAssured.given().header("Content-type", "application/json").body(ingredient);
        response = request.post("http://localhost:8080/ingredient/addIngredient");
    }

    @Then("I receive the json of the saved ingredient")
    public void i_receive_the_json_of_the_saved_ingredient(){
        Assertions.assertEquals(200, response.getStatusCode());
        Ingredient ingredientFromResponse = response.then().extract().as(Ingredient.class);
        Assertions.assertEquals(ingredient.getName(), ingredientFromResponse.getName());
        Assertions.assertEquals(ingredient.getType(), ingredientFromResponse.getType());
        Assertions.assertEquals(ingredient.getAbv(), ingredientFromResponse.getAbv());
        Assertions.assertEquals(ingredient.getStorage(), ingredientFromResponse.getStorage());
        Assertions.assertEquals(ingredient.getDescription(), ingredientFromResponse.getDescription());

        ingredientRepository.deleteById(response.jsonPath().get("id"));
    }

    @Then("I receive an error from the server and the ingredient is not stored")
    public void i_receive_an_error_from_the_server_and_the_ingredient_is_not_stored(){
        Assertions.assertEquals(500, response.getStatusCode());
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredient.getId());
        Assertions.assertTrue(ingredientOptional.isEmpty());
    }

}
