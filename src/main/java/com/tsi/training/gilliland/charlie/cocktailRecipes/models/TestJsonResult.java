package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.tsi.training.gilliland.charlie.cocktailRecipes.models.*;

import java.util.ArrayList;

public class TestJsonResult {

    public static String createWhiskeyAndCoke(){
        // Testing making a whiskey and coke recipe
        // Creating a rocks glass
        Glass rocksGlass = new Glass(GlassType.ROCKS, 45);

        // Creating ingredients
        Ingredient coke = new Ingredient("coca cola", "coke", 0, StorageType.AMBIENT, "Coca Cola brand coke.");
        Ingredient whiskey = new Ingredient("jack daniel's", "whiskey", 40, StorageType.AMBIENT, "A bourbon whiskey from the Jack Daniel's brand.");
        Ingredient ice = new Ingredient("ice", "ice", 0, StorageType.FROZEN, "Some ice... Chilly");

        // Creating a mixing spoon
        Equipment mixingSpoon = new Equipment("mixing spoon", false);

        // Step one
        Instruction getRocksGlass = new Instruction(null, null, rocksGlass, null, "Get the rocks glass.");
        // Step two
        ArrayList<Ingredient> addWhiskeyIngredients = new ArrayList<>();
        addWhiskeyIngredients.add(whiskey);
        Instruction addWhiskeyToGlass = new Instruction(addWhiskeyIngredients, null, null, null, "Add whiskey to the glass");
        // Step three
        ArrayList<Ingredient> addCokeIngredients = new ArrayList<>();
        addCokeIngredients.add(coke);
        Instruction addCokeToGlass = new Instruction(addCokeIngredients, null, null, null, "Add coke to the glass");
        // Step four
        ArrayList<Ingredient> addIceIngredients = new ArrayList<>();
        addIceIngredients.add(ice);
        Instruction addIceToGlass = new Instruction(addIceIngredients, null, null, null, "Add ice to the glass");
        // Step five
        ArrayList<Equipment> addStirEquipment = new ArrayList<>();
        addStirEquipment.add(mixingSpoon);
        Instruction stirDrink = new Instruction(null, addStirEquipment, null, null, "Stir the drink");

        // Creating the array to feed to Cocktail
        Instruction[] whiskeyAndCokeInstructions = {getRocksGlass, addWhiskeyToGlass, addCokeToGlass, addIceToGlass, stirDrink};

        Cocktail whiskeyAndCoke = new Cocktail("Whiskey and Coke", whiskeyAndCokeInstructions, "A shot of whiskey mixed with coke.");

        return whiskeyAndCoke.toString();
    }
}
