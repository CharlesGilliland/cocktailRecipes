package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

public class Equipment {
    int id;
    String name;
    boolean isPowered;

    public Equipment(int id, String name, boolean isPowered){
        this.id = id;
        this.name = name;
        this.isPowered = isPowered;
    }

    public String toString(){
        String json = new Gson().toJson(this);
        return json;
    }

}
