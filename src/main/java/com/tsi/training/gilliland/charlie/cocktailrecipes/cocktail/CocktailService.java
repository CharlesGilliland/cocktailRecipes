package com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail;

import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.InstructionRepository;
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

    String noCocktailWithId = "No cocktail could be found with the given ID";

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
            throw new NoSuchElementException(noCocktailWithId);
        }
        return cocktailOptional.get();
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
        return cocktailRepository.save(cocktail);
    }

    public String updateCocktail(Cocktail cocktail) {
        Optional<Cocktail> cocktailInDb = cocktailRepository.findById(cocktail.getId());
        if (cocktailInDb.isEmpty()) {
            throw new NoSuchElementException(noCocktailWithId);
        }
        cocktailRepository.save(cocktail);
        return "Cocktail Updated";
    }

    public String deleteCocktail(int id) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(id);
        if (cocktailOptional.isEmpty()) {
            throw new NoSuchElementException(noCocktailWithId);
        }
        cocktailRepository.deleteById(id);
        return "Cocktail Deleted";
    }
}
