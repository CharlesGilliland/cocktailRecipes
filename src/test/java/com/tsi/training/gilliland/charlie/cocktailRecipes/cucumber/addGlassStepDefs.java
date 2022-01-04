package com.tsi.training.gilliland.charlie.cocktailRecipes.cucumber;

import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.Glass;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.GlassRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class addGlassStepDefs {
    Glass glass = new Glass();
    String type = "Test";
    int volume = 500;
    RequestSpecification request;
    Response response;
    JsonPath json;

    @Autowired
    GlassRepository glassRepository;

    @Given("I have supplied a type for the glass")
    public void i_have_supplied_a_type_for_the_glass(){
        glass.setType(type);
    }

    @Given("I have not supplied a type for the glass")
    public void i_have_not_supplied_a_type_for_the_glass(){
        glass.setType(null);
    }

    @Given("I have supplied a volume for the glass")
    public void i_have_supplied_a_volume_for_the_glass(){
        glass.setVolume(volume);
    }

    @Given("I have not supplied a volume for the glass")
    public void i_have_not_supplied_a_volume_for_the_glass(){
        glass.setVolume(0);
    }

    @When("I submit a request to add the glass")
    public void i_submit_a_request_to_add_the_glass(){
        request = RestAssured.given().header("Content-Type","application/json").body(glass);
        response = request.post("http://localhost:8080/glass/addGlass");
    }

    @Then("I receive the json of the saved glass")
    public void i_receive_the_json_of_the_saved_glass(){
        Assertions.assertEquals(200, response.getStatusCode());
        Glass glassFromResponse = response.then().extract().as(Glass.class);
        Assertions.assertEquals(glass.getType(), glassFromResponse.getType());
        Assertions.assertEquals(glass.getVolume(), glassFromResponse.getVolume());

        // Deleting the create glass
        glassRepository.deleteById(response.jsonPath().get("id"));
    }

    @Then("I receive an error from the server and the glass is not stored")
    public void i_receive_an_error_from_the_server_with_glass_message_and_the_glass_is_not_stored(){
        Assertions.assertEquals(500, response.getStatusCode());
        Optional<Glass> glassOptional = glassRepository.findById(glass.getId());
        Assertions.assertTrue(glassOptional.isEmpty());
    }

}
