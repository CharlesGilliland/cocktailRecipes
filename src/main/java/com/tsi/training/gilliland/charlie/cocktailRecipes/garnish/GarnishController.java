package com.tsi.training.gilliland.charlie.cocktailRecipes.garnish;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/garnish")
public class GarnishController {

    Gson gson = new Gson();

    @Autowired
    GarnishRepository garnishRepository;

    ////////////////////////////// Garnish //////////////////////////////////////////////
    // This all works with the new RDS Database!!!!
    @GetMapping("/getAll")
    public @ResponseBody
    String getGarnish() {
        return gson.toJson(garnishRepository.findAll());
    }

    @GetMapping("/getGarnish")
    public @ResponseBody
    String getGarnish(@RequestParam int id) {
        Optional<Garnish> garnishOptional = garnishRepository.findById(id);
        if(garnishOptional.isEmpty()){
            return "Not Found";
        }
        return gson.toJson(garnishOptional.get());
    }

    @PostMapping("/addGarnish")
    public @ResponseBody
    String addGarnish(@RequestParam String type, @RequestParam String storage) {
        Garnish garnish = new Garnish();
        garnish.setType(type);
        garnish.setStorage(storage);
        garnishRepository.save(garnish);
        return "Saved";
    }

    @PutMapping("/updateGarnish")
    public @ResponseBody
    String updateGarnish(@RequestParam int id, @RequestBody Garnish garnish) {
        Optional<Garnish> garnishInDbOptional = garnishRepository.findById(id);
        if (garnishInDbOptional.isEmpty()) {
            return "Garnish is not in database";
        }
        Garnish garnishInDb = garnishInDbOptional.get();
        garnishInDb.setType(garnish.getType());
        garnishInDb.setStorage(garnish.getStorage());
        garnishRepository.save(garnishInDb);
        return "Garnish updated";
    }

    @DeleteMapping("/deleteGarnish")
    public @ResponseBody
    String deleteGarnish(@RequestParam int id) {
        Optional<Garnish> garnishInDb = garnishRepository.findById(id);
        if (garnishInDb.isEmpty()) {
            return "Garnish is not in database";
        }
        garnishRepository.deleteById(id);
        return "Garnish deleted";
    }
}
