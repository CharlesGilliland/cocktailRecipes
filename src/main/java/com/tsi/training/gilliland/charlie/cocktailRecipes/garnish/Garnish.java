package com.tsi.training.gilliland.charlie.cocktailRecipes.garnish;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Garnish {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int garnishid;

    String type;
    String storage;

    @JsonIgnore
    @ManyToMany(mappedBy = "garnish")
    Set<Instruction> instructions = new HashSet<>();

    public Garnish(){

    }

    public String toString(){
        return new Gson().toJson(this);
    }

    public Set<Instruction> getInstructions(){
        return this.instructions;
    }

    public int getId(){
        return this.garnishid;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    public String getStorage(){ return this.storage; }
    public void setStorage(String storage){
        this.storage = storage;
    }
}
