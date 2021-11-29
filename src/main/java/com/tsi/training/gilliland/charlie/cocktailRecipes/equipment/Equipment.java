package com.tsi.training.gilliland.charlie.cocktailRecipes.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int equipmentid;

    String name;
    boolean isPowered;

    @JsonIgnore
    @ManyToMany(mappedBy = "equipment")
    Set<Instruction> instructions = new HashSet<>();

    public Equipment(){

    }

    public Equipment(String name, boolean isPowered){
        this.name = name;
        this.isPowered = isPowered;
    }

    public String toString(){
        return new Gson().toJson(this);
    }

    public int getId(){
        return this.equipmentid;
    }
    public void setId(int id){
        this.equipmentid = id;
    }

    public Set<Instruction> getInstructions(){
        return this.instructions;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public boolean getIsPowered(){
        return this.isPowered;
    }
    public void setIsPowered(boolean isPowered){
        this.isPowered = isPowered;
    }

}
