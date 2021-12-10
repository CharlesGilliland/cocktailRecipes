package com.tsi.training.gilliland.charlie.cocktailRecipes.instruction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailRecipes.equipment.Equipment;
import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.Glass;
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

    @ManyToMany
    @JoinTable(
            name = "instruction_glass",
            joinColumns = @JoinColumn(name = "instructionid"),
            inverseJoinColumns = @JoinColumn(name = "glassid")
    )
    private Set<Glass> glasses = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "instruction_garnish",
            joinColumns = @JoinColumn(name = "instructionid"),
            inverseJoinColumns = @JoinColumn(name = "garnishid")
    )
    private Set<Garnish> garnish = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "instructions")
    Set<Cocktail> cocktails = new HashSet<>();


    String description;

    public Instruction(){

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

    public Set<Glass> getGlasses(){
        return this.glasses;
    }
    public void addGlass(Glass glass){
        this.glasses.add(glass);
    }

    public Set<Garnish> getGarnish(){
        return this.garnish;
    }
    public void addGarnish(Garnish garnish){
        this.garnish.add(garnish);
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
