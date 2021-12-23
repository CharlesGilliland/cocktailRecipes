package com.tsi.training.gilliland.charlie.cocktailRecipes.ingredientTests;

import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.Ingredient;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class IngredientTest {
    @Test
    public void testToString(){
        Ingredient ingredient = new Ingredient();
        String expected = "{\"ingredientid\":0,\"abv\":0.0,\"instructions\":[]}";
        String actual = ingredient.toString();
        Assertions.assertEquals(expected, actual);

        ingredient.setName("Russian Standard");
        ingredient.setType("Vodka");
        ingredient.setAbv(40);
        ingredient.setStorage("Ambient");
        ingredient.setDescription("Russian standard vodka");

        expected = "{\"ingredientid\":0,\"name\":\"Russian Standard\",\"type\":\"Vodka\",\"abv\":40.0,\"storage\":\"Ambient\",\"description\":\"Russian standard vodka\",\"instructions\":[]}";
        actual = ingredient.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetInstructions() {
        Ingredient ingredient = new Ingredient();
        Set<Instruction> expected = new HashSet<>();
        Set<Instruction> actual = ingredient.getInstructions();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetId() {
        Ingredient ingredient = new Ingredient();
        int expected = 0;
        int actual = ingredient.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAndSetName() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Russian Standard");
        String expected = "Russian Standard";
        String actual = ingredient.getName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAndSetType() {
        Ingredient ingredient = new Ingredient();
        ingredient.setType("Vodka");
        String expected = "Vodka";
        String actual = ingredient.getType();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAndSetAbv() {
        Ingredient ingredient = new Ingredient();
        ingredient.setAbv(40);
        float expected = 40;
        float actual = ingredient.getAbv();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAndSetStorage() {
        Ingredient ingredient = new Ingredient();
        ingredient.setStorage("Ambient");
        String expected = "Ambient";
        String actual = ingredient.getStorage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAndSetDescription() {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("A short description");
        String expected = "A short description";
        String actual = ingredient.getDescription();
        Assertions.assertEquals(expected, actual);
    }

}