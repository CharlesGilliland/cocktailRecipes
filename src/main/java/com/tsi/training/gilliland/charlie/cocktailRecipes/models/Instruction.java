package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

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
        return new Gson().toJson(this);
    }

}
