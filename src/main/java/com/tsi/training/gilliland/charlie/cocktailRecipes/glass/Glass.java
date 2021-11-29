package com.tsi.training.gilliland.charlie.cocktailRecipes.glass;

import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Glass {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int glassid;

    private String type;
    private int volume;

    public Glass(){

    }

    public Glass(String t, int v){
        this.type = t;
        this.volume = v;
    }

    public String toString(){
        return new Gson().toJson(this);
    }

    public int getId(){
        return this.glassid;
    }
    public void setId(int id){
        this.glassid = id;
    }

    public String getType(){
        return  this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    public int getVolume(){
        return this.volume;
    }
    public void setVolume(int volume){
        this.volume = volume;
    }
}
