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

    public Instruction(){

    }

    public Instruction(int id, ArrayList<Ingredient> ingredients, ArrayList<Equipment> equipment, Glass glass, Garnish garnish, String description){
        this.id = id;
        this.ingredients = ingredients;
        this.equipment = equipment;
        this.glass = glass;
        this.garnish = garnish;
        this.description = description;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public ArrayList<Ingredient> getIngredients(){
        return this.ingredients;
    }
    public void setIngredients(ArrayList<Ingredient> ingredients){
        this.ingredients = ingredients;
    }
    public void setIngredient(Ingredient ingredient){ this.ingredients.add(ingredient); }

    public ArrayList<Equipment> getEquipment(){
        return this.equipment;
    }
    public void setEquipment(ArrayList<Equipment> equipment){
        this.equipment = equipment;
    }
    public void setSingleEquipment(Equipment singleEquipment){ this.equipment.add(singleEquipment); }

    public Garnish getGarnish(){
        return this.garnish;
    }
    public void setGarnish(Garnish garnish){
        this.garnish = garnish;
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
