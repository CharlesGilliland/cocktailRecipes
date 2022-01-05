package com.tsi.training.gilliland.charlie.cocktailrecipes.instructionTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.InstructionController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.InstructionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(InstructionController.class)
public class InstructionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InstructionService instructionService;

    @Test
    public void testGetAllEmpty() throws Exception {
        when(instructionService.getAllInstructions()).thenReturn(new ArrayList<Instruction>());
        mockMvc.perform(get("/instruction/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void testGetAllWithInstruction() throws Exception {
        Instruction instruction1 = new Instruction();
        Instruction instruction2 = new Instruction();
        List<Instruction> instructionList = new ArrayList<Instruction>();
        instructionList.add(instruction1);
        instructionList.add(instruction2);
        when(instructionService.getAllInstructions()).thenReturn(instructionList);
        mockMvc.perform(get("/instruction/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null},{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}]")));
    }

    @Test
    public void testGetInstruction() throws Exception {
        Instruction instruction = new Instruction();
        when(instructionService.getInstruction(instruction.getId())).thenReturn(instruction);
        mockMvc.perform(get("/instruction/getInstruction?id=" + instruction.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}")));
    }

    @Test
    public void testAddInstruction() throws Exception {
        mockMvc.perform(post("/instruction/addInstruction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}"))
                .andExpect(status().isOk());
        verify(instructionService).addInstructions(any(Instruction.class));
    }

    @Test
    public void testUpdateInstruction() throws Exception {
        mockMvc.perform(put("/instruction/updateInstruction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Instruction().toString()))
                .andExpect(status().isOk());
        verify(instructionService).updateInstruction(any(Instruction.class));
    }

    @Test
    public void testDeleteInstruction() throws Exception {
        when(instructionService.deleteInstruction(2)).thenReturn("Instruction Deleted");
        mockMvc.perform(delete("/instruction/deleteInstruction?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Instruction Deleted")));
        verify(instructionService).deleteInstruction(2);
    }
}
