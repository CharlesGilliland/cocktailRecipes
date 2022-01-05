package com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return cocktailService.getCocktail(id);
    }

    @PostMapping("/addCocktail")
    public @ResponseBody
    Cocktail addCocktail(@RequestBody Cocktail cocktail) {
        return cocktailService.addCocktail(cocktail);
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