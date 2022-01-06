package com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail;

import com.google.gson.Gson;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cocktail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int id;

    @ManyToMany
    @JoinTable(
            name = "cocktail_instruction",
            joinColumns = @JoinColumn(name = "cocktailid"),
            inverseJoinColumns = @JoinColumn(name = "instructionid")
    )
    Set<Instruction> instructions = new HashSet<>();

    String name;
    String description;
    int noOfSteps;

    public String toString(){
        return new Gson().toJson(this);
    }

    public int getId(){
        return this.id;
    }

    public Set<Instruction> getInstructions(){
        return this.instructions;
    }
    public void addInstruction(Instruction instruction){
        this.instructions.add(instruction);
    }

    public void setNoOfSteps(int steps){
        this.noOfSteps = steps;
    }
    public int getNoOfSteps(){ return this.noOfSteps; }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){ return this.description; }
    public void setDescription(String description){
        this.description = description;
    }




}