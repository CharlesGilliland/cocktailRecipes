package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

public class Cocktail {
    String name;
    int totalSteps;
    Instruction[] instructions;
    String description;

    public Cocktail(String name, Instruction[] instructions, String description){
        this.name = name;
        this.instructions = instructions;
        this.totalSteps = totalSteps = instructions.length;
        this.description = description;
    }

    public String toString(){
        return new Gson().toJson(this);
    }


}
