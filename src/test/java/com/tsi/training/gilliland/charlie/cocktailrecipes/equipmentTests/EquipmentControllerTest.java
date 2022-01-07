package com.tsi.training.gilliland.charlie.cocktailrecipes.equipmentTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.Equipment;
import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.EquipmentController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.EquipmentService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = EquipmentController.class)
@WebAppConfiguration
public class EquipmentControllerTest {
    @Mock
    EquipmentService equipmentService;

    @Mock
    EquipmentController equipmentController = new EquipmentController();

    private MockMvc mockMvc;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(equipmentController).build();
    }

    private static Equipment createEquipmentHelper(String name, boolean isPowered){
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setIsPowered(isPowered);
        return equipment;
    }

    @Test
    void testGetAll() throws Exception {
        when(equipmentService.getAllEquipment()).thenReturn(new ArrayList<Equipment>());
        mockMvc.perform(get("/equipment/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEquipment() throws Exception {
        Equipment equipment =  createEquipmentHelper("Tester", true);
        mockMvc.perform(get("/equipment/getEquipment?id=" + equipment.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void testAddEquipment() throws Exception {
        mockMvc.perform(post("/equipment/addEquipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"name\":\"Tester\",\"isPowered\":true}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateEquipment() throws Exception {
        mockMvc.perform(put("/equipment/updateEquipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createEquipmentHelper("Tester", true).toString()))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEquipment() throws Exception {
        mockMvc.perform(delete("/equipment/deleteEquipment?id=2"))
                .andExpect(status().isOk());
    }

}
