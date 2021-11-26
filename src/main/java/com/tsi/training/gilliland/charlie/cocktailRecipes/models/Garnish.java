package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

public class Garnish {
    int id;
    GarnishType type;
    StorageType storage;

    public Garnish(){

    }

    public Garnish(int id, GarnishType t, StorageType s) {
        this.id = id;
        this.type = t;
        this.storage = s;
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

    public GarnishType getGarnish(){
        return this.type;
    }
    public void setGarnish(GarnishType type){
        this.type = type;
    }

    public StorageType getStorage(){
        return this.storage;
    }
    public void setStorage(StorageType storage){
        this.storage = storage;
    }

}



enum GarnishType {
    CHERRY,
    OLIVE,
    LIME,
    LEMON,
    ORANGE,
    CREAM,
    UMBRELLA
}
