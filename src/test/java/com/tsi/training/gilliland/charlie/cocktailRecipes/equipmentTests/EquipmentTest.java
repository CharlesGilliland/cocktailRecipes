package com.tsi.training.gilliland.charlie.cocktailRecipes.equipmentTests;

import com.tsi.training.gilliland.charlie.cocktailRecipes.equipment.Equipment;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class EquipmentTest {
    @Test
    public void testToString(){
        Equipment equipment = new Equipment();
        String expected = "{\"id\":0,\"isPowered\":false,\"instructions\":[]}";
        String actual = equipment.toString();
        Assertions.assertEquals(expected, actual);

        equipment.setName("Cocktail Shaker");
        expected = "{\"id\":0,\"name\":\"Cocktail Shaker\",\"isPowered\":false,\"instructions\":[]}";
        actual = equipment.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetId() {
        Equipment equipment = new Equipment();
        int expected = 0;
        int actual = equipment.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetInstructions() {
        Equipment equipment = new Equipment();
        Set<Instruction> expected = new HashSet<>();
        Set<Instruction> actual = equipment.getInstructions();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAndSetName() {
        Equipment equipment = new Equipment();
        equipment.setName("Cocktail Shaker");
        String expected = "Cocktail Shaker";
        String actual = equipment.getName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAndSetIsPowered() {
        Equipment equipment = new Equipment();
        equipment.setIsPowered(true);
        boolean expected = true;
        boolean actual = equipment.getIsPowered();
        Assertions.assertEquals(expected, actual);
    }
}
