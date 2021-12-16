package com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    IngredientRepository ingredientRepository;

    //////////////////////////////// Ingredient //////////////////////////////////////////////
    // These all work with the API
    @GetMapping("/getAll")
    public @ResponseBody
    Iterable<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping("/getIngredient")
    public @ResponseBody
    Object getIngredient(@RequestParam int id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
        if (ingredientOptional.isEmpty()) {
            return "Ingredient not found";
        }
        Ingredient ingredient = ingredientOptional.get();
        return ingredient;
    }

    @PostMapping("/addIngredient")
    public @ResponseBody
    String addIngredient(@RequestParam String name, @RequestParam String type,
                         @RequestParam float abv, String storage, String description) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setType(type);
        ingredient.setAbv(abv);
        ingredient.setStorage(storage);
        ingredient.setDescription(description);

        ingredientRepository.save(ingredient);
        return "Saved";
    }

    @PutMapping("/updateIngredient")
    public @ResponseBody
    String updateIngredient(@RequestBody Ingredient ingredient) {
        Optional<Ingredient> ingredientInDb = ingredientRepository.findById(ingredient.getId());
        if (ingredientInDb.isEmpty()) {
            return "Ingredient is not in database";
        }
        ingredientRepository.save(ingredient);
        return "Ingredient Updated";
    }

    @DeleteMapping("/deleteIngredient")
    public @ResponseBody
    String deleteIngredient(@RequestParam int id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isEmpty()) {
            return "No ingredient found";
        }
        ingredientRepository.deleteById(id);
        return "Ingredient deleted";
    }
}
