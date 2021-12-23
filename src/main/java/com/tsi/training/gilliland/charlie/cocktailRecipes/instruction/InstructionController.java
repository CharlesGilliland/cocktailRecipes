package com.tsi.training.gilliland.charlie.cocktailRecipes.instruction;

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
    private InstructionService instructionService;

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
    List<Instruction> getInstructions() {
        return instructionService.getAllInstructions();
    }

    @GetMapping("/getInstruction")
    public @ResponseBody
    Instruction getInstruction(@RequestParam int id) {
        return instructionService.getInstruction(id);
    }

    @PostMapping("/addInstruction")
    public @ResponseBody
    String addInstruction(@RequestBody Instruction instruction) {
        return instructionService.addInstructions(instruction);
    }

    @PutMapping("/updateInstruction")
    public @ResponseBody
    String updateInstruction(@RequestBody Instruction instruction) {
        return instructionService.updateInstruction(instruction);
    }

    @DeleteMapping("/deleteInstruction")
    public @ResponseBody
    String deleteInstruction(@RequestParam int id) {
        return instructionService.deleteInstruction(id);
    }
}
