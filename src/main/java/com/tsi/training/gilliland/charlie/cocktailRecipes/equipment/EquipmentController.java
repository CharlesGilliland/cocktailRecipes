package com.tsi.training.gilliland.charlie.cocktailRecipes.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
@CrossOrigin
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    //////////////////////////////// Equipment //////////////////////////////////////////////
    @GetMapping("/getAll")
    public @ResponseBody
    List<Equipment> getEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/getEquipment")
    public @ResponseBody
    Equipment getEquipment(@RequestParam int id) {
        return equipmentService.getEquipment(id);
    }

    @PostMapping("/addEquipment")
    public @ResponseBody
    String addEquipment(@RequestBody Equipment equipment) {
        return equipmentService.addEquipment(equipment);
    }

    @PutMapping("/updateEquipment")
    public @ResponseBody
    String updateEquipment(@RequestBody Equipment equipment) {
        return equipmentService.updateEquipment(equipment);
    }

    @DeleteMapping("/deleteEquipment")
    public @ResponseBody
    String deleteEquipment(@RequestParam int id) {
        return equipmentService.deleteEquipment(id);
    }
}
