package com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return ingredientService.getIngredient(id);
    }

    @PostMapping("/addIngredient")
    public @ResponseBody
    String addIngredient(@RequestBody Ingredient ingredient) {
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
