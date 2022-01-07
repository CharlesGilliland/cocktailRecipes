package com.tsi.training.gilliland.charlie.cocktailrecipes.instructionTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.InstructionController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.InstructionService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = InstructionController.class)
@WebAppConfiguration
public class InstructionControllerTest {
    @Mock
    InstructionService instructionService;

    @Mock
    InstructionController instructionController = new InstructionController();

    private MockMvc mockMvc;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(instructionController).build();
    }
    @Test
    void testGetAll() throws Exception {
        when(instructionService.getAllInstructions()).thenReturn(new ArrayList<Instruction>());
        mockMvc.perform(get("/instruction/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    void testGetInstruction() throws Exception {
        Instruction instruction = new Instruction();
        when(instructionService.getInstruction(instruction.getId())).thenReturn(instruction);
        mockMvc.perform(get("/instruction/getInstruction?id=" + instruction.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void testAddInstruction() throws Exception {
        mockMvc.perform(post("/instruction/addInstruction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateInstruction() throws Exception {
        mockMvc.perform(put("/instruction/updateInstruction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Instruction().toString()))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteInstruction() throws Exception {
        mockMvc.perform(delete("/instruction/deleteInstruction?id=2"))
                .andExpect(status().isOk());
    }
}
