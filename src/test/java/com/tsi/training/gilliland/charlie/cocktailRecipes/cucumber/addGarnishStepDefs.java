package com.tsi.training.gilliland.charlie.cocktailRecipes.cucumber;

import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.GarnishRepository;
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
public class addGarnishStepDefs {
    Garnish garnish = new Garnish();
    String type = "Tester";
    String storage = "Ambient";
    RequestSpecification request;
    Response response;

    @Autowired
    GarnishRepository garnishRepository;

    @Given("I have supplied a type for the garnish")
    public void i_have_supplied_a_type_for_the_garnish(){
        garnish.setType(type);
    }

    @Given("I have not supplied a type for the garnish")
    public void i_have_not_supplied_a_type_for_the_garnish(){
        garnish.setType(null);
    }

    @Given("I have supplied storage for the garnish")
    public void i_have_supplied_storage_for_the_garnish(){
        garnish.setStorage(storage);
    }

    @Given("I have not supplied storage for the garnish")
    public void i_have_not_supplied_storage_for_the_garnish(){
        garnish.setStorage(null);
    }

    @When("I submit a request to add the garnish")
    public void i_submit_a_request_to_add_the_garnish(){
        request = RestAssured.given().header("Content-Type","application/json").body(garnish);
        response = request.post("http://localhost:8080/garnish/addGarnish");
    }

    @Then("I receive the json of the saved garnish")
    public void i_receive_the_json_of_the_saved_garnish(){
        Assertions.assertEquals(200, response.getStatusCode());
        Garnish garnishFromResponse = response.then().extract().as(Garnish.class);
        Assertions.assertEquals(garnish.getType(), garnishFromResponse.getType());
        Assertions.assertEquals(garnish.getStorage(), garnishFromResponse.getStorage());

        garnishRepository.deleteById(response.jsonPath().get("id"));
    }

    @Then("I receive an error from the server and the garnish is not stored")
    public void i_should_receive_an_error_from_the_server_and_the_garnish_is_not_stored(){
        Assertions.assertEquals(500, response.getStatusCode());
        Optional<Garnish> garnishOptional = garnishRepository.findById(garnish.getId());
        Assertions.assertTrue(garnishOptional.isEmpty());
    }
}
