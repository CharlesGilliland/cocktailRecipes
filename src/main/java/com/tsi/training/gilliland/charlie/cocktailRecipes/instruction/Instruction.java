package com.tsi.training.gilliland.charlie.cocktailRecipes.instruction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailRecipes.equipment.Equipment;
import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.Ingredient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int instructionid;

    @ManyToMany
    @JoinTable(
            name="instruction_ingredient",
            joinColumns = @JoinColumn(name = "instructionid"),
            inverseJoinColumns = @JoinColumn(name = "ingredientid")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="instruction_equipment",
            joinColumns = @JoinColumn(name = "instructionid"),
            inverseJoinColumns = @JoinColumn(name = "equipmentid")
    )
    private Set<Equipment> equipment = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "instructions")
    Set<Cocktail> cocktails = new HashSet<>();


    Integer glassId;
    Integer garnishId;
    String description;

    public Instruction(){

    }

    public Instruction(Integer glassId, Integer garnishId, String description){
        this.glassId = glassId;
        this.garnishId = garnishId;
        this.description = description;
    }


    public int getId(){
        return this.instructionid;
    }
    public void setId(int id){
        this.instructionid = id;
    }

    public Set<Ingredient> getIngredients(){
        return this.ingredients;
    }
    public void addIngredients(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public Set<Equipment> getEquipment(){
        return this.equipment;
    }
    public void addEquipment(Equipment equipment){
        this.equipment.add(equipment);
    }

    public Integer getGarnish(){
        return this.garnishId;
    }
    public void setGarnish(Integer garnish){
        this.garnishId = garnish;
    }

    public Integer getGlass(){
        return this.glassId;
    }
    public void setGlass(Integer glass){
        this.glassId = glass;
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
