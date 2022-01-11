package com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cocktail")
@CrossOrigin
public class CocktailController {

    @Autowired
    CocktailService cocktailService;

    @GetMapping("/getAll")
    public @ResponseBody
    List<Cocktail> getAll() {
        return cocktailService.getAll();
    }

    @GetMapping("/getCocktail")
    public @ResponseBody
    Cocktail getCocktail(@RequestParam int id) {
        try {
            return cocktailService.getCocktail(id);
        } catch (NoSuchElementException e){
            throw e;
        }
    }

    @PostMapping("/addCocktail")
    public @ResponseBody
    Cocktail addCocktail(@RequestBody Cocktail cocktail) {
        try {
            return cocktailService.addCocktail(cocktail);
        } catch (IllegalArgumentException e){
            throw e;
        }
    }

    @PutMapping("/updateCocktail")
    public @ResponseBody
    String updateCocktail(@RequestBody Cocktail cocktail) {
        return cocktailService.updateCocktail(cocktail);
    }

    @DeleteMapping("/deleteCocktail")
    public @ResponseBody
    String deleteCocktail(@RequestParam int id) {
        return cocktailService.deleteCocktail(id);
    }
}
