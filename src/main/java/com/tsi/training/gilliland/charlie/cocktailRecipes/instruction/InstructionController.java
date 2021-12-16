package com.tsi.training.gilliland.charlie.cocktailRecipes.instruction;

import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.CocktailRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.equipment.Equipment;
import com.tsi.training.gilliland.charlie.cocktailRecipes.equipment.EquipmentRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.GarnishRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.Glass;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.GlassRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.Ingredient;
import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instruction")
public class InstructionController {

    @Autowired
    private GlassRepository glassRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private GarnishRepository garnishRepository;
    @Autowired
    private InstructionRepository instructionRepository;


    //////////////////////////////// Instruction //////////////////////////////////////////
    @GetMapping("/getAll")
    public @ResponseBody
    Iterable<Instruction> getInstructions() {
        return instructionRepository.findAll();
    }

    @GetMapping("/getInstruction")
    public @ResponseBody
    Object getInstruction(@RequestParam int id) {
        Optional<Instruction> instructionOptional = instructionRepository.findById(id);
        if (instructionOptional.isEmpty()) {
            return "Instruction not found";
        }
        Instruction instruction = instructionOptional.get();
        return instruction;
    }

    @PostMapping("/addInstruction")
    public @ResponseBody
    String addInstruction(@RequestParam(required = false) List<Integer> ingredientIds,
                          @RequestParam(required = false) List<Integer> equipmentIds,
                          @RequestParam(required = false) List<Integer> glassIds,
                          @RequestParam(required = false) List<Integer> garnishIds,
                          @RequestParam(required = false) String description) {

        // Create instruction
        Instruction instruction = new Instruction();
        // Finding Equipment
        if (!equipmentIds.isEmpty()) {
            for (Integer i : equipmentIds) {
                Optional<Equipment> singleEquipmentOptional = equipmentRepository.findById(i);
                if (singleEquipmentOptional.isPresent()) {
                    Equipment singleEquipment = singleEquipmentOptional.get();
                    instruction.addEquipment(singleEquipment);
                }
            }
        }
        // Getting Ingredients
        if (!ingredientIds.isEmpty()) {
            for (Integer i : ingredientIds) {
                Optional<Ingredient> singleIngredientOptional = ingredientRepository.findById(i);
                if (singleIngredientOptional.isPresent()) {
                    Ingredient singleIngredient = singleIngredientOptional.get();
                    instruction.addIngredients(singleIngredient);
                }
            }
        }
        //Finding glass
        if (!glassIds.isEmpty()) {
            for (Integer i : glassIds) {
                Optional<Glass> glassOptional = glassRepository.findById(i);
                if (glassOptional.isPresent()) {
                    Glass singleGlass = glassOptional.get();
                    instruction.addGlass(singleGlass);
                }
            }
        }
        // Finding garnish
        if (!garnishIds.isEmpty()) {
            for (Integer i : garnishIds) {
                Optional<Garnish> garnishOptional = garnishRepository.findById(i);
                if (garnishOptional.isPresent()) {
                    Garnish singleGarnish = garnishOptional.get();
                    instruction.addGarnish(singleGarnish);
                }
            }
        }
        instruction.setDescription(description);
        instructionRepository.save(instruction);
        return "Instruction Added";
    }

    @PutMapping("/updateInstruction")
    public @ResponseBody
    String updateInstruction(@RequestBody Instruction instruction) {
        Optional<Instruction> instructionInDb = instructionRepository.findById(instruction.getId());
        if (instructionInDb.isEmpty()) {
            return "Instruction not found";
        }
        instructionRepository.save(instruction);
        return "Instruction updated";
    }

    @DeleteMapping("/deleteInstruction")
    public @ResponseBody
    String deleteInstruction(@RequestParam int id) {
        Optional<Instruction> instruction = instructionRepository.findById(id);
        if (instruction.isEmpty()) {
            return "Instruction not found";
        }
        instructionRepository.deleteById(id);
        return "Instruction deleted";
    }
}
