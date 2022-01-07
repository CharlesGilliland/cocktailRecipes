package com.tsi.training.gilliland.charlie.cocktailrecipes.equipmentTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.Equipment;
import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.EquipmentController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.EquipmentService;
import org.mockito.InjectMocks;
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
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = EquipmentController.class)
@WebAppConfiguration
public class EquipmentControllerTest {
    @Mock
    EquipmentService equipmentService;

    @InjectMocks
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
    void testGetAllEmpty() throws Exception {
        when(equipmentService.getAllEquipment()).thenReturn(new ArrayList<Equipment>());
        mockMvc.perform(get("/equipment/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    void testGetAllWithEquipment() throws Exception {
        Equipment equipment1 = createEquipmentHelper("Tester", true);
        Equipment equipment2 = createEquipmentHelper("Tester", false);
        List<Equipment> equipmentList = new ArrayList<Equipment>();
        equipmentList.add(equipment1);
        equipmentList.add(equipment2);
        when(equipmentService.getAllEquipment()).thenReturn(equipmentList);
        mockMvc.perform(get("/equipment/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":0,\"name\":\"Tester\",\"isPowered\":true},{\"id\":0,\"name\":\"Tester\",\"isPowered\":false}]")));
    }

    @Test
    void testGetEquipment() throws Exception {
        Equipment equipment =  createEquipmentHelper("Tester", true);
        when(equipmentService.getEquipment(equipment.getId())).thenReturn(equipment);
        mockMvc.perform(get("/equipment/getEquipment?id=" + equipment.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":0,\"name\":\"Tester\",\"isPowered\":true}")));
    }

    @Test
    void testAddEquipment() throws Exception {
        mockMvc.perform(post("/equipment/addEquipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"name\":\"Tester\",\"isPowered\":true}"))
                .andExpect(status().isOk());
        verify(equipmentService).addEquipment(any(Equipment.class));
    }

    @Test
    void testUpdateEquipment() throws Exception {
        mockMvc.perform(put("/equipment/updateEquipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createEquipmentHelper("Tester", true).toString()))
                .andExpect(status().isOk());
        verify(equipmentService).updateEquipment(any(Equipment.class));
    }

    @Test
    void testDeleteEquipment() throws Exception {
        when(equipmentService.deleteEquipment(2)).thenReturn("Equipment Deleted");
        mockMvc.perform(delete("/equipment/deleteEquipment?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Equipment Deleted")));
        verify(equipmentService).deleteEquipment(2);
    }

}
