package com.tsi.training.gilliland.charlie.cocktailRecipes;

import com.google.gson.Gson;

import java.util.ArrayList;

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
        String json = new Gson().toJson(this);
        return  json;
    }


}
