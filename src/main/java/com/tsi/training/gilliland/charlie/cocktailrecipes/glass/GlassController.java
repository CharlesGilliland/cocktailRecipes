package com.tsi.training.gilliland.charlie.cocktailrecipes.glass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/glass")
@CrossOrigin
public class GlassController {

    @Autowired
    GlassService glassService;

    //////////////////////////////// Glasses //////////////////////////////////////////////
    // These all work with the API
    @GetMapping("/getAll")
    public @ResponseBody
    List<Glass> getGlasses() {
        return glassService.getGlasses();
    }

    @GetMapping("/getGlass")
    public @ResponseBody
    Glass getGlass(@RequestParam int id) {
        return glassService.getGlass(id);
    }

    @PostMapping("/addGlass")
    public @ResponseBody
    Glass addGlass(@RequestBody Glass glass) {
        return glassService.addGlass(glass);
    }

    @PutMapping("/updateGlass")
    public @ResponseBody
    String updateGlass(@RequestBody Glass glass) {
        return glassService.updateGlass(glass);
    }

    @DeleteMapping("/deleteGlass")
    public @ResponseBody
    String deleteGlass(@RequestParam int id) {
        return glassService.deleteGlass(id);
    }

}
