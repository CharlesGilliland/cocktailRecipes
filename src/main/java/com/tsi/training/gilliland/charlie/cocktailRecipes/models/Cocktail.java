package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Cocktail {
    int id;
    String name;
    int totalSteps;
    ArrayList<Instruction> instructions;
    String description;

    public Cocktail(){

    }

    public Cocktail(int id, String name, ArrayList<Instruction> instructions, String description){
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.totalSteps = totalSteps = instructions.size();
        this.description = description;
    }

    public String toString(){
        return new Gson().toJson(this);
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

    public ArrayList<Instruction> getInstructions(){
        return this.instructions;
    }
    public void setInstructions(ArrayList<Instruction> instructions){
        this.instructions = instructions;
    }
    public void setInstruction(int index, Instruction instruction){
        this.instructions.set(index, instruction);
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }




}
