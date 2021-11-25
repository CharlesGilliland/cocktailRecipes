package com.tsi.training.gilliland.charlie.cocktailRecipes;

import com.google.gson.Gson;

public class Equipment {
    String name;
    boolean isPowered;

    public Equipment(String name, boolean isPowered){
        this.name = name;
        this.isPowered = isPowered;
    }

    public String toString(){
        String json = new Gson().toJson(this);
        return json;
    }

}
