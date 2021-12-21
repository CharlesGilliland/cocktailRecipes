package com.tsi.training.gilliland.charlie.cocktailRecipes.glass;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/glass")
public class GlassController {

    @Autowired
    GlassService glassService;

    Gson gson = new Gson();

    @Autowired
    private GlassRepository glassRepository;

    //////////////////////////////// Glasses //////////////////////////////////////////////
    // These all work with the API
    @GetMapping("/getAll")
    public @ResponseBody
    List<Glass> getGlasses() {
        List<Glass> glasses = glassService.getGlasses();
        return glasses;
    }

    @GetMapping("/getGlass")
    public @ResponseBody
    Glass getGlass(@RequestParam int id) {
        Glass glass = glassService.getGlass(id);
        return glass;
    }

    @PostMapping("/addGlass")
    public @ResponseBody
    String addGlass(@RequestBody Glass glass) {
        String response = glassService.addGlass(glass);
        return response;
    }

    @PutMapping("/updateGlass")
    public @ResponseBody
    String updateGlass(@RequestBody Glass glass) {
        String response = glassService.updateGlass(glass);
        return response;
    }

    @DeleteMapping("/deleteGlass")
    public @ResponseBody
    String deleteGlass(@RequestParam int id) {
        String response = glassService.deleteGlass(id);
        return response;
    }

}
