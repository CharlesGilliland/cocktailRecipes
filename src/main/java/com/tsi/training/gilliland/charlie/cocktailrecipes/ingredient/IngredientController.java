package com.tsi.training.gilliland.charlie.cocktailrecipes.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ingredient")
@CrossOrigin
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    //////////////////////////////// Ingredient //////////////////////////////////////////////
    // These all work with the API
    @GetMapping("/getAll")
    public @ResponseBody
    Iterable<Ingredient> getIngredients() {
        return ingredientService.getIngredients();
    }

    @GetMapping("/getIngredient")
    public @ResponseBody
    Object getIngredient(@RequestParam int id) {
        try {
            return ingredientService.getIngredient(id);
        } catch(Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }

    }

    @PostMapping("/addIngredient")
    public @ResponseBody
    Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/updateIngredient")
    public @ResponseBody
    String updateIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(ingredient);
    }

    @DeleteMapping("/deleteIngredient")
    public @ResponseBody
    String deleteIngredient(@RequestParam int id) {
        return ingredientService.deleteIngredient(id);
    }
}
