package com.tsi.training.gilliland.charlie.cocktailRecipes.instruction;

import com.tsi.training.gilliland.charlie.cocktailRecipes.equipment.EquipmentRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.GarnishRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.GlassRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InstructionService {

    public InstructionService(InstructionRepository instructionRepository) { this.instructionRepository = instructionRepository; }

    @Autowired
    InstructionRepository instructionRepository;
    @Autowired
    GlassRepository glassRepository;
    @Autowired
    GarnishRepository garnishRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    EquipmentRepository equipmentRepository;

    public List<Instruction> getAllInstructions() {
        return instructionRepository.findAll();
    }

    public Instruction getInstruction(int id) {
        Optional<Instruction> instructionOptional = instructionRepository.findById(id);
        if (instructionOptional.isEmpty()) {
            throw new NoSuchElementException("No instruction could be found with the given ID");
        }
        Instruction instruction = instructionOptional.get();
        return instruction;
    }

    public String addInstructions(Instruction instruction) {
        instructionRepository.save(instruction);
        return "Saved";
    }

    public String updateInstruction(Instruction instruction) {
        Optional<Instruction> instructionInDb = instructionRepository.findById(instruction.getId());
        if (instructionInDb.isEmpty()) {
            throw new NoSuchElementException("No instruction could be found with the given ID");
        }
        instructionRepository.save(instruction);
        return "Instruction Updated";
    }

    public String deleteInstruction(int id) {
        Optional<Instruction> instruction = instructionRepository.findById(id);
        if (instruction.isEmpty()) {
            throw new NoSuchElementException("No instruction could be found with the given ID");
        }
        instructionRepository.deleteById(id);
        return "Instruction Deleted";
    }
}
