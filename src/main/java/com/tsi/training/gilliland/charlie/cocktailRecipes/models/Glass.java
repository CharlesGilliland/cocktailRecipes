package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

public class Glass {
    int id;
    GlassType type;
    int volume;

    public Glass(int id, GlassType t, int v){
        this.id = id;
        this.type = t;
        this.volume = v;
    }

    public String toString(){
        return new Gson().toJson(this);
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