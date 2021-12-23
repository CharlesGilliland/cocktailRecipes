package com.tsi.training.gilliland.charlie.cocktailRecipes.equipment;

import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EquipmentService {

    public EquipmentService(EquipmentRepository equipmentRepository) { this.equipmentRepository = equipmentRepository; }

    @Autowired
    EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipment(int id){
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(id);
        if (equipmentOptional.isEmpty()) {
            throw new NoSuchElementException("No equipment could be found with the given ID");
        }
        Equipment equipment = equipmentOptional.get();
        return equipment;
    }

    public String addEquipment(Equipment equipment) {
        equipmentRepository.save(equipment);
        return "Saved";
    }

    public String updateEquipment(Equipment equipment) {
        Optional<Equipment> equipmentInDb = equipmentRepository.findById(equipment.getId());
        if (equipmentInDb.isEmpty()) {
            throw new NoSuchElementException("No equipment could be found with the given ID");
        }
        equipmentRepository.save(equipment);
        return "Equipment Updated";
    }

    public String deleteEquipment(int id) {
        Optional<Equipment> equipmentInDb = equipmentRepository.findById(id);
        if (equipmentInDb.isEmpty()) {
            throw new NoSuchElementException("No equipment could be found with the given ID");
        }
        equipmentRepository.deleteById(id);
        return "Equipment Deleted";
    }

}
