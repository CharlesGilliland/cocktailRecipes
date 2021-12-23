package com.tsi.training.gilliland.charlie.cocktailRecipes.instructionTests;

import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.Ingredient;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.InstructionRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.InstructionService;
import io.cucumber.java.bs.I;
import io.cucumber.java.sl.In;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InstructionServiceTest {

    @Mock
    private InstructionRepository instructionRepository;

    private InstructionService instructionService;

    @BeforeEach
    void setUp() {
        instructionService = new InstructionService(instructionRepository);
    }

    @Test
    public void testGetAllInstructions() {
        instructionService.getAllInstructions();
        verify(instructionRepository).findAll();
    }

    @Test
    public void testGetInstruction() {
        Instruction instruction = new Instruction();
        given(instructionRepository.findById(instruction.getId())).willReturn(Optional.of(instruction));
        Instruction actual = instructionService.getInstruction(instruction.getId());
        Assertions.assertEquals(instruction, actual);
        verify(instructionRepository).findById(instruction.getId());
    }

    @Test
    public void testGetInstructionNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            instructionService.getInstruction(anyInt());
        });
        String expected = "No instruction could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAddInstruction() {
        Instruction instruction = new Instruction();
        String expected = "Saved";
        String actual = instructionService.addInstructions(instruction);
        ArgumentCaptor<Instruction> instructionArgumentCaptor = ArgumentCaptor.forClass(Instruction.class);
        verify(instructionRepository).save(instructionArgumentCaptor.capture());
        Instruction capturedInstruction = instructionArgumentCaptor.getValue();
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(instruction, capturedInstruction);
    }

    @Test
    public void testUpdateInstruction() {
        Instruction instruction = new Instruction();
        given(instructionRepository.findById(instruction.getId())).willReturn(Optional.of(instruction));
        instructionService.addInstructions(instruction);
        instruction.setDescription("Updating the instruction");
        ArgumentCaptor<Instruction> instructionArgumentCaptor = ArgumentCaptor.forClass(Instruction.class);
        String expected = "Instruction Updated";
        String actual = instructionService.updateInstruction(instruction);
        verify(instructionRepository, atLeast(2)).save(instructionArgumentCaptor.capture());
        verify(instructionRepository).findById(instruction.getId());
        Instruction capturedInstruction = instructionArgumentCaptor.getValue();
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(instruction, capturedInstruction);
    }

    @Test
    public void testUpdateInstructionNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            instructionService.updateInstruction(new Instruction());
        });
        String expected = "No instruction could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteInstruction() {
        Instruction instruction = new Instruction();
        given(instructionRepository.findById(instruction.getId())).willReturn(Optional.of(instruction));
        String expected = "Instruction Deleted";
        String actual = instructionService.deleteInstruction(instruction.getId());
        Assertions.assertEquals(expected, actual);
        verify(instructionRepository).deleteById(instruction.getId());
    }

    @Test
    public void testDeleteInstructionNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            instructionService.deleteInstruction(anyInt());
        });
        String expected = "No instruction could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
