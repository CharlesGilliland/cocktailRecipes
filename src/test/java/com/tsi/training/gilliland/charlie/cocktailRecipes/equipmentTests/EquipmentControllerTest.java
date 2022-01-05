package com.tsi.training.gilliland.charlie.cocktailRecipes.equipmentTests;

import com.tsi.training.gilliland.charlie.cocktailRecipes.equipment.Equipment;
import com.tsi.training.gilliland.charlie.cocktailRecipes.equipment.EquipmentService;
import com.tsi.training.gilliland.charlie.cocktailRecipes.equipment.EquipmentController;
import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.Garnish;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EquipmentController.class)
public class EquipmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EquipmentService equipmentService;

    private static Equipment createEquipmentHelper(String name, boolean isPowered){
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setIsPowered(isPowered);
        return equipment;
    }

    @Test
    public void testGetAllEmpty() throws Exception {
        when(equipmentService.getAllEquipment()).thenReturn(new ArrayList<Equipment>());
        mockMvc.perform(get("/equipment/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void testGetAllWithEquipment() throws Exception {
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
    public void testGetEquipment() throws Exception {
        Equipment equipment =  createEquipmentHelper("Tester", true);
        when(equipmentService.getEquipment(equipment.getId())).thenReturn(equipment);
        mockMvc.perform(get("/equipment/getEquipment?id=" + equipment.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":0,\"name\":\"Tester\",\"isPowered\":true}")));
    }

    @Test
    public void testAddEquipment() throws Exception {
        mockMvc.perform(post("/equipment/addEquipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"name\":\"Tester\",\"isPowered\":true}"))
                .andExpect(status().isOk());
        verify(equipmentService).addEquipment(any(Equipment.class));
    }

    @Test
    public void testUpdateEquipment() throws Exception {
        mockMvc.perform(put("/equipment/updateEquipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createEquipmentHelper("Tester", true).toString()))
                .andExpect(status().isOk());
        verify(equipmentService).updateEquipment(any(Equipment.class));
    }

    @Test
    public void testDeleteEquipment() throws Exception {
        when(equipmentService.deleteEquipment(2)).thenReturn("Equipment Deleted");
        mockMvc.perform(delete("/equipment/deleteEquipment?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Equipment Deleted")));
        verify(equipmentService).deleteEquipment(2);
    }

}
