package com.tsi.training.gilliland.charlie.cocktailrecipes.equipmentTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.Equipment;
import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.EquipmentRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.equipment.EquipmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    private EquipmentService equipmentService;

    @BeforeEach
    void setUp() {
        equipmentService = new EquipmentService(equipmentRepository);
    }

    @Test
    public void testGetAllEquipment() {
        equipmentService.getAllEquipment();
        verify(equipmentRepository).findAll();
    }

    @Test
    public void testGetEquipment() {
        Equipment equipment = new Equipment();
        given(equipmentRepository.findById(equipment.getId())).willReturn(Optional.of(equipment));
        Equipment expected = equipmentService.getEquipment(equipment.getId());
        Assertions.assertEquals(expected, equipment);
        verify(equipmentRepository).findById(equipment.getId());
    }

    @Test
    public void testGetEquipmentNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            equipmentService.getEquipment(anyInt());
        });
        String expected = "No equipment could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAddEquipment() {
        Equipment equipment = new Equipment();
        equipment.setName("Cocktail Shaker");
        equipmentService.addEquipment(equipment);
        ArgumentCaptor<Equipment> equipmentArgumentCaptor = ArgumentCaptor.forClass(Equipment.class);
        verify(equipmentRepository).save(equipmentArgumentCaptor.capture());
        Equipment capturedEquipment = equipmentArgumentCaptor.getValue();
        Assertions.assertEquals(equipment, capturedEquipment);
    }

    @Test
    public void testUpdateEquipment() {
        Equipment equipment = new Equipment();
        equipment.setName("Tester");
        given(equipmentRepository.findById(equipment.getId())).willReturn(Optional.of(equipment));
        equipmentService.addEquipment(equipment);
        equipment.setName("Cocktail Shaker");
        ArgumentCaptor<Equipment> equipmentArgumentCaptor = ArgumentCaptor.forClass(Equipment.class);
        String expected = "Equipment Updated";
        String actual = equipmentService.updateEquipment(equipment);
        verify(equipmentRepository, atLeast(2)).save(equipmentArgumentCaptor.capture());
        verify(equipmentRepository).findById(equipment.getId());
        Equipment capturedEquipment = equipmentArgumentCaptor.getValue();
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(equipment, capturedEquipment);
    }

    @Test
    public void testUpdateEquipmentNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            equipmentService.updateEquipment(new Equipment());
        });
        String expected = "No equipment could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteEquipment() {
        Equipment equipment = new Equipment();
        given(equipmentRepository.findById(equipment.getId())).willReturn(Optional.of(equipment));
        String expected = "Equipment Deleted";
        String actual = equipmentService.deleteEquipment(equipment.getId());
        Assertions.assertEquals(expected, actual);
        verify(equipmentRepository).deleteById(equipment.getId());
    }

    @Test
    public void testDeleteEquipmentNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            equipmentService.deleteEquipment(anyInt());
        });
        String expected = "No equipment could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
