package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;
import com.tsi.training.gilliland.charlie.cocktailRecipes.models.Equipment;
import com.tsi.training.gilliland.charlie.cocktailRecipes.models.Garnish;
import com.tsi.training.gilliland.charlie.cocktailRecipes.models.Glass;
import com.tsi.training.gilliland.charlie.cocktailRecipes.models.Ingredient;

import java.util.ArrayList;

public class Instruction {
    int id;
    ArrayList<Ingredient> ingredients;
    ArrayList<Equipment> equipment;
    Glass glass;
    Garnish garnish;
    String description;

    public Instruction(int id, ArrayList<Ingredient> ingredients, ArrayList<Equipment> equipment, Glass glass, Garnish garnish, String description){
        this.id = id;
        this.ingredients = ingredients;
        this.equipment = equipment;
        this.glass = glass;
        this.garnish = garnish;
        this.description = description;
    }

    public String toString(){
        String json = new Gson().toJson(this);
        return json;
    }

}
