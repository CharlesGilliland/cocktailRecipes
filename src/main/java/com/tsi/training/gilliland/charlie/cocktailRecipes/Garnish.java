package com.tsi.training.gilliland.charlie.cocktailRecipes;

import com.google.gson.Gson;

public class Garnish {
    GarnishType type;
    StorageType storage;


    public Garnish(GarnishType t, StorageType s) {
        this.type = t;
        this.storage = s;
    }

    public String toString() {
        String json = new Gson().toJson(this);
        return json;
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
