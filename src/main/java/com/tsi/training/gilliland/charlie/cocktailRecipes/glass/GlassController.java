package com.tsi.training.gilliland.charlie.cocktailRecipes.glass;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/glass")
public class GlassController {

    Gson gson = new Gson();

    @Autowired
    private GlassRepository glassRepository;

    //////////////////////////////// Glasses //////////////////////////////////////////////
    // These all work with the API
    @GetMapping("/getAll")
    public @ResponseBody
    Iterable<Glass> getGlasses() {
        return glassRepository.findAll();
    }

    @GetMapping("/getGlass")
    public @ResponseBody
    String getGlass(@RequestParam int id) {
        return gson.toJson(glassRepository.findById(id).get());
    }

    @PostMapping("/addGlass")
    public @ResponseBody
    String addGlass(@RequestParam String type, @RequestParam int volume) {
        Glass glass = new Glass();
        glass.setType(type);
        glass.setVolume(volume);
        glassRepository.save(glass);
        return "Saved";
    }

    @PutMapping("/updateGlass")
    public @ResponseBody
    String updateGlass(@RequestBody Glass glass) {
        Optional<Glass> glassInDb = glassRepository.findById(glass.getId());
        if (glassInDb.isEmpty()) {
            return "Glass is not in database";
        }
        glassRepository.save(glass);
        return "Glass Updated";
    }

    @DeleteMapping("/deleteGlass")
    public @ResponseBody
    String deleteGlass(@RequestParam int id) {
        Optional<Glass> glassInDb = glassRepository.findById(id);
        if (glassInDb.isEmpty()) {
            return "Glass not found";
        }
        glassRepository.deleteById(id);
        return "Glass Deleted";
    }

}
