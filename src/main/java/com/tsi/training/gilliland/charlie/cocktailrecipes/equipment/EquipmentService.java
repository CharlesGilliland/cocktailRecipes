package com.tsi.training.gilliland.charlie.cocktailrecipes.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EquipmentService {

    public EquipmentService(EquipmentRepository equipmentRepository) { this.equipmentRepository = equipmentRepository; }

    String noEquipmentWithId = "No equipment could be found with the given ID";

    @Autowired
    EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipment(int id){
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(id);
        if (equipmentOptional.isEmpty()) {
            throw new NoSuchElementException(noEquipmentWithId);
        }
        return equipmentOptional.get();
    }

    public Equipment addEquipment(Equipment equipment) {
        if(equipment.getName() == "" || equipment.getName() == null){
            throw new IllegalArgumentException("Please provide a name for the equipment");
        }
        return equipmentRepository.save(equipment);
    }

    public String updateEquipment(Equipment equipment) {
        Optional<Equipment> equipmentInDb = equipmentRepository.findById(equipment.getId());
        if (equipmentInDb.isEmpty()) {
            throw new NoSuchElementException(noEquipmentWithId);
        }
        equipmentRepository.save(equipment);
        return "Equipment Updated";
    }

    public String deleteEquipment(int id) {
        Optional<Equipment> equipmentInDb = equipmentRepository.findById(id);
        if (equipmentInDb.isEmpty()) {
            throw new NoSuchElementException(noEquipmentWithId);
        }
        equipmentRepository.deleteById(id);
        return "Equipment Deleted";
    }

}
