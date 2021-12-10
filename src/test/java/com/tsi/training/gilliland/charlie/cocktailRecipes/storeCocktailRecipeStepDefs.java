package com.tsi.training.gilliland.charlie.cocktailRecipes;

import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.Cocktail;

// New imports :)
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.CocktailRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.InstructionRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CucumberContextConfiguration
@SpringBootTest
public class storeCocktailRecipeStepDefs {

    @Autowired
    CocktailRepository cocktailRepository;

    @Autowired
    InstructionRepository instructionRepository;

    private String name;
    private List<Integer> instructions = new ArrayList<Integer>();
    private String description;
    private Cocktail cocktail;
    private String response;


    @Given("I supply a name")
    public void i_supply_a_name(){
        name = "Vodka and Coke";
    }
    @Given("I supply a set of instructions")
    public void i_supply_a_set_of_instructions(){
        instructions.add(8);
        instructions.add(13);
        instructions.add(10);
    }
    @Given("I supply a description")
    public void i_supply_a_description(){
        description = "A vodka and coke";
    }
    @When("I add the cocktail")
    public void i_add_the_cocktail(){
        cocktail = new Cocktail();
        cocktail.setName(name);
        cocktail.setDescription(description);
        for(Integer i : instructions){
            Optional<Instruction> instructionOptional = instructionRepository.findById(i);
            if(instructionOptional.isPresent()){
                cocktail.addInstruction(instructionOptional.get());
            }
        }
        cocktailRepository.save(cocktail);
    }
    @Then("the cocktail recipe should be stored")
    public void the_cocktail_recipe_should_be_stored(){
        Assertions.assertTrue(cocktailRepository.existsById(cocktail.getId()));
    }
}
