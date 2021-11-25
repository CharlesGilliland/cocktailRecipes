package com.tsi.training.gilliland.charlie.cocktailRecipes.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class PrettyJsonHelper {
    public static String toPrettyJsonString(String s){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(s);
        return gson.toJson(je);
    }
}
