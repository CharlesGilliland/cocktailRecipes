package com.tsi.training.gilliland.charlie.cocktailrecipes.instructionTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.InstructionRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.InstructionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InstructionServiceTest {

    @Mock
    private InstructionRepository instructionRepository;

    @InjectMocks
    private InstructionService instructionService;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
        instructionService = new InstructionService(instructionRepository);
    }

    @Test
    void testGetAllInstructions() {
        instructionService.getAllInstructions();
        verify(instructionRepository).findAll();
    }

    @Test
    void testGetInstruction() {
        Instruction instruction = new Instruction();
        given(instructionRepository.findById(instruction.getId())).willReturn(Optional.of(instruction));
        Instruction actual = instructionService.getInstruction(instruction.getId());
        Assertions.assertEquals(instruction, actual);
        verify(instructionRepository).findById(instruction.getId());
    }

    @Test
    void testGetInstructionNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            instructionService.getInstruction(anyInt());
        });
        String expected = "No instruction could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddInstruction() {
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
    void testUpdateInstruction() {
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
    void testUpdateInstructionNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            instructionService.updateInstruction(new Instruction());
        });
        String expected = "No instruction could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDeleteInstruction() {
        Instruction instruction = new Instruction();
        given(instructionRepository.findById(instruction.getId())).willReturn(Optional.of(instruction));
        String expected = "Instruction Deleted";
        String actual = instructionService.deleteInstruction(instruction.getId());
        Assertions.assertEquals(expected, actual);
        verify(instructionRepository).deleteById(instruction.getId());
    }

    @Test
    void testDeleteInstructionNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            instructionService.deleteInstruction(anyInt());
        });
        String expected = "No instruction could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
