package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

public class Ingredient {
    int id;
    String name;
    String type;
    int abv;
    StorageType storage;
    String description;

    public Ingredient(){

    }

    public Ingredient(int id, String name, String type, int abv, StorageType storage, String desc){
        this.id = id;
        this.name = name;
        this.type = type;
        this.abv = abv;
        this.storage = storage;
        this.description = desc;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    public int getAbv(){
        return this.abv;
    }
    public void setAbv(int abv){
        this.abv = abv;
    }

    public StorageType getStorage(){
        return this.storage;
    }
    public void setStorage(StorageType storage){
        this.storage = storage;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String toString(){
        return new Gson().toJson(this);
    }
}
