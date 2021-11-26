package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.tsi.training.gilliland.charlie.cocktailRecipes.models.*;

import java.util.ArrayList;

public class TestJsonResult {

    public static String createSexOnTheBeach(){
        // Testing making a sex on the beach recipe
        // Creating a rocks glass
        Glass highballGlass = new Glass(1, GlassType.HIGHBALL, 35);

        // Creating ingredients
        Ingredient vodka = new Ingredient(1, "russian standard", "vodka", 38, StorageType.AMBIENT, "Russian Standard Vodka");
        Ingredient peachSchnapps = new Ingredient(2, "archers peach schnapps", "schnapps", 18, StorageType.AMBIENT, "Archers Peach Schnapps");
        Ingredient oj = new Ingredient(3, "orange juice", "juice", 0, StorageType.CHILLED, "Fresh oj, just like mamma used to make.");
        Ingredient cranberry = new Ingredient(4, "cranberry juice", "juice", 0, StorageType.CHILLED, "Fresh cranberry juice, deeeeelish");
        Ingredient ice = new Ingredient(5, "ice", "ice", 0, StorageType.FROZEN, "Some ice, just ice");

        // Creating a mixing spoon
        Equipment mixingSpoon = new Equipment(1, "mixing spoon", false);

        // Step one
        Instruction getHighballGlass = new Instruction(1, null, null, highballGlass, null, "Get the highball glass.");
        // Step two
        ArrayList<Ingredient> addVodkaAndSchnappsIngredients = new ArrayList<>();
        addVodkaAndSchnappsIngredients.add(vodka);
        addVodkaAndSchnappsIngredients.add(peachSchnapps);
        Instruction addVodkaAndSchnappsToGlass = new Instruction(2, addVodkaAndSchnappsIngredients, null, null, null, "Add the vodka and peach schnapps");
        // Step three
        ArrayList<Ingredient> addJuiceIngredients = new ArrayList<>();
        addJuiceIngredients.add(oj);
        addJuiceIngredients.add(cranberry);
        Instruction addJuiceToGlass = new Instruction(3, addJuiceIngredients, null, null, null, "Add the oj and cranberry juice");
        // Step four
        ArrayList<Ingredient> addIceIngredients = new ArrayList<>();
        addIceIngredients.add(ice);
        Instruction addIceToGlass = new Instruction(4, addIceIngredients, null, null, null, "Add ice to the glass");
        // Step five
        ArrayList<Equipment> addStirEquipment = new ArrayList<>();
        addStirEquipment.add(mixingSpoon);
        Instruction stirDrink = new Instruction(5, null, addStirEquipment, null, null, "Stir the drink");

        // Creating the array to feed to Cocktail
        ArrayList<Instruction> SexOnTheBeachInstructions = new ArrayList<>();
        SexOnTheBeachInstructions.add(getHighballGlass);
        SexOnTheBeachInstructions.add(addVodkaAndSchnappsToGlass);
        SexOnTheBeachInstructions.add(addJuiceToGlass);
        SexOnTheBeachInstructions.add(addIceToGlass);
        SexOnTheBeachInstructions.add(stirDrink);


        Cocktail sexOnTheBeach = new Cocktail(1, "Sex on the Beach", SexOnTheBeachInstructions, "A highball glass containing sex on the beach.");

        return sexOnTheBeach.toString();
    }

    public static String createWhiskeyAndCoke(){
        // Testing making a whiskey and coke recipe
        // Creating a rocks glass
        Glass rocksGlass = new Glass(1, GlassType.ROCKS, 45);

        // Creating ingredients
        Ingredient coke = new Ingredient(1, "coca cola", "coke", 0, StorageType.AMBIENT, "Coca Cola brand coke.");
        Ingredient whiskey = new Ingredient(2, "jack daniel's", "whiskey", 40, StorageType.AMBIENT, "A bourbon whiskey from the Jack Daniel's brand.");
        Ingredient ice = new Ingredient(3, "ice", "ice", 0, StorageType.FROZEN, "Some ice... Chilly");

        // Creating a mixing spoon
        Equipment mixingSpoon = new Equipment(1, "mixing spoon", false);

        // Step one
        Instruction getRocksGlass = new Instruction(1, null, null, rocksGlass, null, "Get the rocks glass.");
        // Step two
        ArrayList<Ingredient> addWhiskeyIngredients = new ArrayList<>();
        addWhiskeyIngredients.add(whiskey);
        Instruction addWhiskeyToGlass = new Instruction(2, addWhiskeyIngredients, null, null, null, "Add whiskey to the glass");
        // Step three
        ArrayList<Ingredient> addCokeIngredients = new ArrayList<>();
        addCokeIngredients.add(coke);
        Instruction addCokeToGlass = new Instruction(3, addCokeIngredients, null, null, null, "Add coke to the glass");
        // Step four
        ArrayList<Ingredient> addIceIngredients = new ArrayList<>();
        addIceIngredients.add(ice);
        Instruction addIceToGlass = new Instruction(4, addIceIngredients, null, null, null, "Add ice to the glass");
        // Step five
        ArrayList<Equipment> addStirEquipment = new ArrayList<>();
        addStirEquipment.add(mixingSpoon);
        Instruction stirDrink = new Instruction(5, null, addStirEquipment, null, null, "Stir the drink");

        // Creating the array to feed to Cocktail
        ArrayList<Instruction> whiskeyAndCokeInstructions = new ArrayList<>();
        whiskeyAndCokeInstructions.add(getRocksGlass);
        whiskeyAndCokeInstructions.add(addWhiskeyToGlass);
        whiskeyAndCokeInstructions.add(addCokeToGlass);
        whiskeyAndCokeInstructions.add(addIceToGlass);
        whiskeyAndCokeInstructions.add(stirDrink);

        Cocktail whiskeyAndCoke = new Cocktail(1, "Whiskey and Coke", whiskeyAndCokeInstructions, "A shot of whiskey mixed with coke.");

        return whiskeyAndCoke.toString();
    }

}
