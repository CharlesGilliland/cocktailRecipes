package com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int ingredientid;

    String name;
    String type;
    float abv;
    String storage;
    String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    Set<Instruction> instructions = new HashSet<>();

    public Ingredient(){

    }

    public Ingredient(String name, String type, float abv, String storage, String desc){
        this.name = name;
        this.type = type;
        this.abv = abv;
        this.storage = storage;
        this.description = desc;
    }

    public Set<Instruction> getInstructions(){
        return this.instructions;
    }

    public int getId(){
        return this.ingredientid;
    }
    public void setId(int id){
        this.ingredientid = id;
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

    public float getAbv(){
        return this.abv;
    }
    public void setAbv(int abv){
        this.abv = abv;
    }

    public String getStorage(){
        return this.storage;
    }
    public void setStorage(String storage){
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
