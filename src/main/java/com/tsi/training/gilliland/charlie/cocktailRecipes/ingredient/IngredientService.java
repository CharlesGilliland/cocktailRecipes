package com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IngredientService {

    public IngredientService(IngredientRepository ingredientRepository) { this.ingredientRepository = ingredientRepository; }

    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredient(int id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
        if (ingredientOptional.isEmpty()) {
            throw new NoSuchElementException("No ingredient could be found with the given ID");
        }
        Ingredient ingredient = ingredientOptional.get();
        return ingredient;
    }

    public String addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
        return "Saved";
    }

    public String updateIngredient(Ingredient ingredient) {
        Optional<Ingredient> ingredientInDb = ingredientRepository.findById(ingredient.getId());
        if (ingredientInDb.isEmpty()) {
            throw new NoSuchElementException("No ingredient could be found with the given ID");
        }
        ingredientRepository.save(ingredient);
        return "Ingredient Updated";
    }

    public String deleteIngredient(int id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isEmpty()) {
            throw new NoSuchElementException("No ingredient could be found with the given ID");
        }
        ingredientRepository.deleteById(id);
        return "Ingredient Deleted";
    }
}
