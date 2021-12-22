package com.tsi.training.gilliland.charlie.cocktailRecipes.glass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Glass {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int glassid;

    @JsonIgnore
    @ManyToMany(mappedBy = "glasses")
    Set<Instruction> instructions = new HashSet<>();

    private String type;
    private int volume;


    public Glass(){

    }

    public String toString(){
        return new Gson().toJson(this);
    }

    public Set<Instruction> getInstructions(){
        return this.instructions;
    }

    public int getId(){
        return this.glassid;
    }

    public String getType(){
        return  this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    public int getVolume(){
        return this.volume;
    }
    public void setVolume(int volume){
        this.volume = volume;
    }
}
