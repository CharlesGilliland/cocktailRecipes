package com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail;

import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CocktailService {

    public CocktailService(CocktailRepository cocktailRepository, InstructionRepository instructionRepository)
    {
        this.cocktailRepository = cocktailRepository;
        this.instructionRepository = instructionRepository;
    }

    @Autowired
    CocktailRepository cocktailRepository;

    @Autowired
    InstructionRepository instructionRepository;

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

    public Cocktail addCocktail(Cocktail cocktail) {
        if(cocktail.getName().isEmpty()){
            throw new IllegalArgumentException("Please provide a name for the cocktail");
        }
        if(cocktail.getInstructions().isEmpty()) {
            throw new IllegalArgumentException("Please provide instructions for the cocktail");
        }
        for(Instruction i : cocktail.getInstructions()){
            if(instructionRepository.findById(i.getId()).isEmpty()){
                instructionRepository.save(i);
            }
        }
        Cocktail returnedCocktail = cocktailRepository.save(cocktail);
        return returnedCocktail;
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
