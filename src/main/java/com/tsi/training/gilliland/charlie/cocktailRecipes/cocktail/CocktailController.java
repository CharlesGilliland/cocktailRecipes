package com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail;

import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.Ingredient;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cocktail")
public class CocktailController {

    // Repositories
    @Autowired
    private InstructionRepository instructionRepository;
    @Autowired
    private CocktailRepository cocktailRepository;

    // Endpoints
    @GetMapping("/getAll")
    public @ResponseBody
    Iterable<Cocktail> getAll() {
        return cocktailRepository.findAll();
    }

    @GetMapping("/getCocktail")
    public @ResponseBody
    Object getCocktail(@RequestParam int id) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(id);
        if (!cocktailOptional.isPresent()) {
            return "Cocktail not found";
        }
        Cocktail cocktail = cocktailOptional.get();
        return cocktail;
    }

    @GetMapping("/getCocktailByName")
    public @ResponseBody Object getCocktailByName(String name){
        Cocktail matchedCocktail;
        Iterable<Cocktail> cocktailsInDb = cocktailRepository.findAll();
        for(Cocktail c : cocktailsInDb){
            if(c.getName().equals(name));
            matchedCocktail = c;
            return matchedCocktail;
        }
        return "Not found";
    }

    @GetMapping("/searchCocktailsByName")
    public @ResponseBody
    Iterable<Cocktail> searchCocktailsByName(String query) {
        Iterable<Cocktail> cocktails = cocktailRepository.findAll();
        ArrayList<Cocktail> results = new ArrayList<>();
        for (Cocktail c : cocktails) {
            if (c.getName().contains(query)) {
                results.add(c);
            }
        }
        return results;
    }

    @GetMapping("/searchCocktailsByIngredients")
    public @ResponseBody
    Iterable<Cocktail> searchCocktailsByIngredients(String query) {
        Iterable<Cocktail> cocktails = cocktailRepository.findAll();
        ArrayList<Cocktail> results = new ArrayList<>();
        outerloop:
        for (Cocktail c : cocktails) {
            for (Instruction inst : c.getInstructions()) {
                for (Ingredient ing : inst.getIngredients()) {
                    if (ing.getName().contains(query)) {
                        results.add(c);
                        continue outerloop;
                    }
                    if (ing.getType().contains(query)) {
                        results.add(c);
                        continue outerloop;
                    }
                }
            }
        }
        return results;
    }

    @PostMapping("/addCocktail")
    public @ResponseBody
    String addCocktail(@RequestParam String name, @RequestParam List<Integer> instructionIds,
                       @RequestParam(required = false) String description) {

        // Create cocktail
        Cocktail cocktail = new Cocktail();

        // Setting the name
        cocktail.setName(name);

        // Setting the instructions
        int stepNumber = 1;
        if (instructionIds != null) {
            for (Integer i : instructionIds) {
                Optional<Instruction> instructionOptional = instructionRepository.findById(i);
                if (instructionOptional.isPresent()) {
                    Instruction singleInstruction = instructionOptional.get();
                    cocktail.addInstruction(singleInstruction);
                }
            }
        }
        cocktail.setNoOfSteps(instructionIds.size());

        // Setting the description
        cocktail.setDescription(description);

        // Saving the cocktail to the database
        cocktailRepository.save(cocktail);

        return cocktail.getName() + " created";
    }


    @PutMapping("/updateCocktail")
    public @ResponseBody
    String updateCocktail(@RequestBody Cocktail cocktail) {
        Optional<Cocktail> cocktailInDb = cocktailRepository.findById(cocktail.getId());
        if (cocktailInDb.isEmpty()) {
            return "Cocktail not found";
        }
        cocktailRepository.save(cocktail);
        return cocktail.getName() + " updated";
    }

    @DeleteMapping("/deleteCocktail")
    public @ResponseBody
    String deleteCocktail(@RequestParam int id) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(id);
        if (cocktailOptional.isEmpty()) {
            return "Cocktail not found";
        }
        cocktailRepository.deleteById(id);
        return cocktailOptional.get().getName() + " deleted";
    }
}
