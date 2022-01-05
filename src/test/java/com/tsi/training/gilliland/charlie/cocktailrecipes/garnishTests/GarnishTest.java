package com.tsi.training.gilliland.charlie.cocktailrecipes.garnishTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class GarnishTest {

    @Test
    void testToString() {
        Garnish garnish = new Garnish();
        String expected = "{\"id\":0,\"instructions\":[]}";
        String actual = garnish.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetInstructions() {
        Garnish garnish = new Garnish();
        Set<Instruction> expected = new HashSet<>();
        Set<Instruction> actual = garnish.getInstructions();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetId() {
        Garnish garnish = new Garnish();
        int expected = 0;
        int actual = garnish.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAndSetType() {
        Garnish garnish = new Garnish();
        garnish.setType("Umbrella");
        String expected = "Umbrella";
        String actual = garnish.getType();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAndSetStorage() {
        Garnish garnish = new Garnish();
        garnish.setStorage("Chilled");
        String expected = "Chilled";
        String actual = garnish.getStorage();
        Assertions.assertEquals(expected, actual);
    }

}
