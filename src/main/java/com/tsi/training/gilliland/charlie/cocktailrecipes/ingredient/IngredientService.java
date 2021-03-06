package com.tsi.training.gilliland.charlie.cocktailrecipes.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IngredientService {

    public IngredientService(IngredientRepository ingredientRepository) { this.ingredientRepository = ingredientRepository; }

    String noIngredientWithId = "No ingredient could be found with the given ID";

    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredient(int id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
        if (ingredientOptional.isEmpty()) {
            throw new NoSuchElementException(noIngredientWithId);
        }
        return ingredientOptional.get();
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        if(ingredient.getName() == null || ingredient.getName().equals("")){
            throw new IllegalArgumentException("Please supply a name for the ingredient");
        }
        if(ingredient.getType() == null || ingredient.getType().equals("")){
            throw new IllegalArgumentException("Please supply a type for the ingredient");
        }
        if(ingredient.getAbv() < 0 || ingredient.getAbv() > 100){
            throw new IllegalArgumentException("Please supply an abv for the ingredient, between 0 and 100");
        }
        return ingredientRepository.save(ingredient);
    }

    public String updateIngredient(Ingredient ingredient) {
        Optional<Ingredient> ingredientInDb = ingredientRepository.findById(ingredient.getId());
        if (ingredientInDb.isEmpty()) {
            throw new NoSuchElementException(noIngredientWithId);
        }
        ingredientRepository.save(ingredient);
        return "Ingredient Updated";
    }

    public String deleteIngredient(int id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isEmpty()) {
            throw new NoSuchElementException(noIngredientWithId);
        }
        ingredientRepository.deleteById(id);
        return "Ingredient Deleted";
    }
}
