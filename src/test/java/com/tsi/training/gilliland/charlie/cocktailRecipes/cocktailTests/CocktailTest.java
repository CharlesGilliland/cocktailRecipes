package com.tsi.training.gilliland.charlie.cocktailRecipes.cocktailTests;

import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


public class CocktailTest {
    @Test
    public void testToString(){
        Cocktail testCocktail = new Cocktail();
        testCocktail.setName("Tester");
        String testString = "{\"id\":0,\"instructions\":[],\"name\":\"Tester\",\"noOfSteps\":0}";
        Assertions.assertEquals(testString, testCocktail.toString(), "toString has produced an unexpected result");
    }

    @Test
    public void testGetId(){
        Cocktail testCocktail = new Cocktail();
        Assertions.assertEquals(0, testCocktail.getId());
    }

    @Test
    public void testAddInstruction(){
        // Creating cocktail, instruction and set
        Cocktail testCocktail = new Cocktail();
        Instruction testInstruction = new Instruction();
        Set<Instruction> testInstructionSet = new HashSet<>();

        // Adding instruction to test cocktail and set
        testCocktail.addInstruction(testInstruction);
        testInstructionSet.add(testInstruction);

        // Checking the two sets are equal (the instruction has been added to the cocktail)
        Assertions.assertEquals(testInstructionSet, testCocktail.getInstructions());

    }

    @Test
    public void testGetInstructions(){
        // Testing Instructions is initialized correctly
        Cocktail testCocktail = new Cocktail();
        Assertions.assertEquals(new HashSet<>(), testCocktail.getInstructions());

        // Creating instructions
        Instruction testInstruction1 = new Instruction();
        Instruction testInstruction2 = new Instruction();

        // Creating set
        Set<Instruction> testInstructionSet = new HashSet<>();
        testInstructionSet.add(testInstruction1);
        testInstructionSet.add(testInstruction2);

        // Adding instructions to cocktail
        testCocktail.addInstruction(testInstruction1);
        testCocktail.addInstruction(testInstruction2);

        // Asserting the two sets are equal
        Assertions.assertEquals(testInstructionSet, testCocktail.getInstructions());
    }

    @Test
    public void testSetNoOfSteps(){
        Cocktail testCocktail = new Cocktail();
        testCocktail.setNoOfSteps(5);
        Assertions.assertEquals(5, testCocktail.getNoOfSteps());
    }

    @Test
    public void testGetNoOfSteps(){
        Cocktail testCocktail = new Cocktail();
        int noOfSteps = testCocktail.getNoOfSteps();
        Assertions.assertEquals(0, noOfSteps);
    }

    @Test
    public void testGetName(){
        Cocktail testCocktail = new Cocktail();
        Assertions.assertEquals(null, testCocktail.getName());
    }

    @Test
    public void testSetName(){
        Cocktail testCocktail = new Cocktail();
        testCocktail.setName("Bloody Mary");
        Assertions.assertEquals("Bloody Mary", testCocktail.getName());
    }

    @Test
    public void testGetDescription(){
        Cocktail testCocktail = new Cocktail();
        Assertions.assertEquals(null, testCocktail.getDescription());
    }

    @Test
    public void testSetDescription(){
        Cocktail testCocktail = new Cocktail();
        testCocktail.setDescription("This is a test.");
        Assertions.assertEquals("This is a test.", testCocktail.getDescription());
    }

}
