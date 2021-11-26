package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

public class Equipment {
    int id;
    String name;
    boolean isPowered;

    public Equipment(){

    }

    public Equipment(int id, String name, boolean isPowered){
        this.id = id;
        this.name = name;
        this.isPowered = isPowered;
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

    public boolean getIsPowered(){
        return this.isPowered;
    }
    public void setIsPowered(boolean isPowered){
        this.isPowered = isPowered;
    }

}
