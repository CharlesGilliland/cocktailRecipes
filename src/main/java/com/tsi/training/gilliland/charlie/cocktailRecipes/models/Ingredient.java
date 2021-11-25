package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

public class Ingredient {
    String name;
    String type;
    int abv;
    StorageType storage;
    String description;

    public Ingredient(String name, String type, int abv, StorageType storage, String desc){
        this.name = name;
        this.type = type;
        this.abv = abv;
        this.storage = storage;
        this.description = desc;
    }

    public String toString(){
        String json = new Gson().toJson(this);
        return json;
    }
}
