package com.tsi.training.gilliland.charlie.cocktailrecipes.cucumber;

import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.Equipment;
import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.EquipmentRepository;
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
public class addEquipmentStep {
    Equipment equipment = new Equipment();
    String name = "Tester";
    Boolean isPowered = true;
    RequestSpecification request;
    Response response;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Given("I have supplied a name for the equipment")
    public void i_have_supplied_a_name_for_the_equipment(){
        equipment.setName(name);
    }

    @Given("I have not supplied a name for the equipment")
    public void i_have_not_supplied_a_name_for_the_equipment(){
        equipment.setName(null);
    }

    @Given("I have supplied a value for if it is powered")
    public void i_have_supplied_a_value_for_if_it_is_powered(){
        equipment.setIsPowered(isPowered);
    }

    @Given("I have not supplied a value for if it is powered")
    public void i_have_not_supplied_a_value_for_if_it_is_powered(){
    }

    @When("I submit a request to add the equipment")
    public void i_submit_a_request_to_add_the_equipment(){
        request = RestAssured.given().header("Content-type", "application/json").body(equipment);
        response = request.post("http://localhost:8080/equipment/addEquipment");
    }

    @Then("I receive the json of the saved equipment")
    public void i_receive_the_json_of_the_saved_equipment(){
        Assertions.assertEquals(200, response.getStatusCode());
        Equipment equipmentFromResponse = response.then().extract().as(Equipment.class);
        Assertions.assertEquals(equipment.getName(), equipmentFromResponse.getName());
        Assertions.assertEquals(equipment.getIsPowered(), equipmentFromResponse.getIsPowered());

        equipmentRepository.deleteById(response.jsonPath().get("id"));
    }

    @Then("I receive an error from the server and the equipment is not stored")
    public void i_receive_an_error_from_the_server_and_the_equipment_is_not_stored(){
        Assertions.assertEquals(500, response.getStatusCode());
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipment.getId());
        Assertions.assertTrue(equipmentOptional.isEmpty());
    }

}
