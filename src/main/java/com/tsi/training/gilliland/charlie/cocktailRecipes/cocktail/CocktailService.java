package com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CocktailService {

    public CocktailService(CocktailRepository cocktailRepository) { this.cocktailRepository = cocktailRepository; }

    @Autowired
    CocktailRepository cocktailRepository;

    public List<Cocktail> getAll(){
        return cocktailRepository.findAll();
    }

    public Cocktail getCocktail(int id) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(id);
        if (!cocktailOptional.isPresent()) {
            throw new NoSuchElementException("No cocktail could be found with the given ID");
        }
        Cocktail cocktail = cocktailOptional.get();
        return cocktail;
    }

    public String addCocktail(Cocktail cocktail) {
        if(cocktail.getName() == null){
            return "Please provide a name for the cocktail";
        }
        if(cocktail.getInstructions().isEmpty()) {
            return "Please provide instructions for the cocktail";
        }
        cocktailRepository.save(cocktail);
        return "Saved";
    }

    public String updateCocktail(Cocktail cocktail) {
        Optional<Cocktail> cocktailInDb = cocktailRepository.findById(cocktail.getId());
        if (cocktailInDb.isEmpty()) {
            throw new NoSuchElementException("No cocktail could be found with the given ID");
        }
        cocktailRepository.save(cocktail);
        return "Cocktail Updated";
    }

    public String deleteCocktail(int id) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(id);
        if (cocktailOptional.isEmpty()) {
            throw new NoSuchElementException("No cocktail could be found with the given ID");
        }
        cocktailRepository.deleteById(id);
        return "Cocktail Deleted";
    }
}
