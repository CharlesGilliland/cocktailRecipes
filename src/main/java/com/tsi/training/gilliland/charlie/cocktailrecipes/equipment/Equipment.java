package com.tsi.training.gilliland.charlie.cocktailrecipes.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int id;

    String name;
    boolean isPowered;

    @JsonIgnore
    @ManyToMany(mappedBy = "equipment")
    Set<Instruction> instructions = new HashSet<>();

    public String toString(){
        return new Gson().toJson(this);
    }

    public int getId(){
        return this.id;
    }

    public Set<Instruction> getInstructions(){
        return this.instructions;
    }

    public String getName() { return this.name; }
    public void setName(String name){
        this.name = name;
    }

    public boolean getIsPowered() { return this.isPowered; }
    public void setIsPowered(boolean isPowered){
        this.isPowered = isPowered;
    }
}
