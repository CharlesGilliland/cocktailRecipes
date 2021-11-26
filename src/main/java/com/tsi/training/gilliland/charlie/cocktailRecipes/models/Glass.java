package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

public class Glass {
    int id;
    GlassType type;
    int volume;

    public Glass(){

    }

    public Glass(int id, GlassType t, int v){
        this.id = id;
        this.type = t;
        this.volume = v;
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

    public GlassType getGlass(){
        return  this.type;
    }
    public void setGlass(GlassType type){
        this.type = type;
    }

    public int getVolume(){
        return this.volume;
    }
    public void setVolume(int volume){
        this.volume = volume;
    }
}
enum GlassType {
    MARTINI,
    WINE,
    LARGEWINE,
    GIN,
    ROCKS,
    HIGHBALL,
    BEER
}