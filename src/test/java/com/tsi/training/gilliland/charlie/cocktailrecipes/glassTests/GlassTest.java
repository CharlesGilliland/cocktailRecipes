package com.tsi.training.gilliland.charlie.cocktailrecipes.glassTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.Glass;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class GlassTest {

    @Test
    void testToString(){
        Glass glass = new Glass();
        String expected = "{\"id\":0,\"instructions\":[],\"volume\":0}";
        String actual = glass.toString();
        Assertions.assertEquals(expected, actual);

        glass.setType("Pint");
        glass.setVolume(568);
        String pintExpected = "{\"id\":0,\"instructions\":[],\"type\":\"Pint\",\"volume\":568}";
        String pintActual = glass.toString();
        Assertions.assertEquals(pintExpected, pintActual);
    }

    @Test
    void testGetInstructions() {
        Glass glass = new Glass();
        Set<Instruction> expected = new HashSet<>();
        Set<Instruction> actual = glass.getInstructions();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetId() {
        Glass glass = new Glass();
        int expected = 0;
        int actual = glass.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAndSetType(){
        Glass glass = new Glass();
        glass.setType("Pint");
        String expected = "Pint";
        String actual = glass.getType();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAndSetVolume(){
        Glass glass = new Glass();
        glass.setVolume(500);
        int expected = 500;
        int actual = glass.getVolume();
        Assertions.assertEquals(expected, actual);
    }
}
