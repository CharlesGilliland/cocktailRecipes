package com.tsi.training.gilliland.charlie.cocktailrecipes.instructionTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.Equipment;
import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.Glass;
import com.tsi.training.gilliland.charlie.cocktailrecipes.ingredient.Ingredient;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class InstructionTest {

    private static Instruction createFullInstruction(){
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Russian Standard");
        ingredient.setType("Vodka");
        ingredient.setAbv(40);
        ingredient.setStorage("Ambient");
        ingredient.setDescription("Some Russian Standard vodka");

        Equipment equipment = new Equipment();
        equipment.setName("Blender");
        equipment.setIsPowered(true);

        Glass glass = new Glass();
        glass.setType("Pint");
        glass.setVolume(568);

        Garnish garnish = new Garnish();
        garnish.setType("Umbrella");
        garnish.setStorage("Ambient");

        Instruction instruction = new Instruction();
        instruction.addIngredients(ingredient);
        instruction.addEquipment(equipment);
        instruction.addGlass(glass);
        instruction.addGarnish(garnish);
        instruction.setDescription("This is a test instruction");

        return instruction;
    }

    @Test
    void testToString() {
        Instruction instruction = new Instruction();
        String expected = "{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"cocktails\":[]}";
        String actual = instruction.toString();
        Assertions.assertEquals(expected, actual);

        instruction = createFullInstruction();
        expected = "{\"id\":0,\"ingredients\":[{\"id\":0,\"name\":\"Russian Standard\",\"type\":\"Vodka\",\"abv\":40.0,\"storage\":\"Ambient\",\"description\":\"Some Russian Standard vodka\",\"instructions\":[]}],\"equipment\":[{\"id\":0,\"name\":\"Blender\",\"isPowered\":true,\"instructions\":[]}],\"glasses\":[{\"id\":0,\"instructions\":[],\"type\":\"Pint\",\"volume\":568}],\"garnish\":[{\"id\":0,\"type\":\"Umbrella\",\"storage\":\"Ambient\",\"instructions\":[]}],\"cocktails\":[],\"description\":\"This is a test instruction\"}";
        actual = instruction.toString();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testGetId() {
        Instruction instruction = new Instruction();
        int expected = 0;
        int actual = instruction.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAndAddIngredients() {
        Instruction instruction = new Instruction();
        Ingredient ingredient = new Ingredient();
        ingredient.setType("Vodka");
        instruction.addIngredients(ingredient);
        Set<Ingredient> expected = new HashSet<>();
        expected.add(ingredient);
        Set<Ingredient> actual = instruction.getIngredients();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAndAddEquipment() {
        Instruction instruction = new Instruction();
        Equipment equipment = new Equipment();
        equipment.setName("Blender");
        instruction.addEquipment(equipment);
        Set<Equipment> expected = new HashSet<>();
        expected.add(equipment);
        Set<Equipment> actual = instruction.getEquipment();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAndAddGlasses() {
        Instruction instruction = new Instruction();
        Glass glass = new Glass();
        glass.setType("Pint");
        instruction.addGlass(glass);
        Set<Glass> expected = new HashSet<>();
        expected.add(glass);
        Set<Glass> actual = instruction.getGlasses();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAndAddGarnish() {
        Instruction instruction = new Instruction();
        Garnish garnish = new Garnish();
        garnish.setType("Umbrella");
        instruction.addGarnish(garnish);
        Set<Garnish> expected = new HashSet<>();
        expected.add(garnish);
        Set<Garnish> actual = instruction.getGarnish();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAndSetDescription() {
        Instruction instruction = new Instruction();
        instruction.setDescription("This is a test");
        String expected = "This is a test";
        String actual = instruction.getDescription();
        Assertions.assertEquals(expected, actual);
    }
}
