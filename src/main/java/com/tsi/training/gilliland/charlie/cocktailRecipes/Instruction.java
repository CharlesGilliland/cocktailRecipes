package com.tsi.training.gilliland.charlie.cocktailRecipes;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Instruction {
    ArrayList<Ingredient> ingredients;
    ArrayList<Equipment> equipment;
    Glass glass;
    Garnish garnish;
    String description;

    public Instruction(ArrayList<Ingredient> ingredients, ArrayList<Equipment> equipment, Glass glass, Garnish garnish, String description){
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
