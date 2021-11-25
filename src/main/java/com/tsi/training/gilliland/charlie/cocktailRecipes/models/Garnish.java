package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;

public class Garnish {
    int id;
    GarnishType type;
    StorageType storage;


    public Garnish(int id, GarnishType t, StorageType s) {
        this.id = id;
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
