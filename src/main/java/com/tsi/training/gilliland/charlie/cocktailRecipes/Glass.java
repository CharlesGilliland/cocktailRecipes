package com.tsi.training.gilliland.charlie.cocktailRecipes;

import com.google.gson.Gson;

public class Glass {
    GlassType type;
    int volume;

    public Glass(GlassType t, int v){
        this.type = t;
        this.volume = v;
    }

    public String toString(){
        String json = new Gson().toJson(this);
        return json;
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