package com.tsi.training.gilliland.charlie.cocktailRecipes.equipment;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    Gson gson = new Gson();

    @Autowired
    EquipmentRepository equipmentRepository;

    //////////////////////////////// Equipment //////////////////////////////////////////////
    @GetMapping("/getAll")
    public @ResponseBody
    String getEquipment() {
        return gson.toJson(equipmentRepository.findAll());
    }

    @GetMapping("/getEquipment")
    public @ResponseBody
    String getEquipment(@RequestParam int id) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(id);
        if (equipmentOptional.isEmpty()) {
            return "Equipment not found";
        }
        Equipment equipment = equipmentOptional.get();
        return equipment.toString();
    }

    @PostMapping("/addEquipment")
    public @ResponseBody
    String addEquipment(@RequestParam String name, @RequestParam boolean isPowered) {
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setIsPowered(isPowered);
        equipmentRepository.save(equipment);
        return "Saved";
    }

    @PutMapping("/updateEquipment")
    public @ResponseBody
    String updateEquipment(@RequestBody Equipment equipment) {
        Optional<Equipment> equipmentInDb = equipmentRepository.findById(equipment.getId());
        if (equipmentInDb.isEmpty()) {
            return "Equipment not in database";
        }
        equipmentRepository.save(equipment);
        return "Equipment updated";
    }

    @DeleteMapping("/deleteEquipment")
    public @ResponseBody
    String deleteEquipment(@RequestParam int id) {
        Optional<Equipment> equipmentInDb = equipmentRepository.findById(id);
        if (equipmentInDb.isEmpty()) {
            return "Equipment is not in database";
        }
        equipmentRepository.deleteById(id);
        return "Equipment deleted";
    }
}
