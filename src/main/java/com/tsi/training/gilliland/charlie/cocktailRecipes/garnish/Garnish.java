package com.tsi.training.gilliland.charlie.cocktailRecipes.garnish;

import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Garnish {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int garnishid;

    String type;
    String storage;

    public Garnish(){

    }

    public Garnish(String t, String s) {
        this.type = t;
        this.storage = s;
    }

    public String toString(){
        return new Gson().toJson(this);
    }

    public int getId(){
        return this.garnishid;
    }
    public void setId(int id){
        this.garnishid = id;
    }

    public String getGarnish(){
        return this.type;
    }
    public void setGarnish(String type){
        this.type = type;
    }

    public String getStorage(){
        return this.storage;
    }
    public void setStorage(String storage){
        this.storage = storage;
    }

}
