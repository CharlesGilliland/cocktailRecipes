package com.tsi.training.gilliland.charlie.cocktailrecipes.instruction;

import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.EquipmentRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.GarnishRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.GlassRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InstructionService {

    public InstructionService(InstructionRepository instructionRepository) { this.instructionRepository = instructionRepository; }

    String noInstructionWithId = "No instruction could be found with the given ID";

    @Autowired
    InstructionRepository instructionRepository;

    public List<Instruction> getAllInstructions() {
        return instructionRepository.findAll();
    }

    public Instruction getInstruction(int id) {
        Optional<Instruction> instructionOptional = instructionRepository.findById(id);
        if (instructionOptional.isEmpty()) {
            throw new NoSuchElementException(noInstructionWithId);
        }
        return instructionOptional.get();
    }

    public String addInstructions(Instruction instruction) {
        instructionRepository.save(instruction);
        return "Saved";
    }

    public String updateInstruction(Instruction instruction) {
        Optional<Instruction> instructionInDb = instructionRepository.findById(instruction.getId());
        if (instructionInDb.isEmpty()) {
            throw new NoSuchElementException(noInstructionWithId);
        }
        instructionRepository.save(instruction);
        return "Instruction Updated";
    }

    public String deleteInstruction(int id) {
        Optional<Instruction> instruction = instructionRepository.findById(id);
        if (instruction.isEmpty()) {
            throw new NoSuchElementException(noInstructionWithId);
        }
        instructionRepository.deleteById(id);
        return "Instruction Deleted";
    }
}
