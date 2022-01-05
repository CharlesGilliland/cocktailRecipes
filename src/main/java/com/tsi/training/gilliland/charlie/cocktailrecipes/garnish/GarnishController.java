package com.tsi.training.gilliland.charlie.cocktailrecipes.garnish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garnish")
@CrossOrigin
public class GarnishController {

    @Autowired
    GarnishService garnishService;

    ////////////////////////////// Garnish //////////////////////////////////////////////
    // This all works with the new RDS Database!!!!
    @GetMapping("/getAll")
    public @ResponseBody
    List<Garnish> getAllGarnish() {
        return garnishService.getAllGarnish();
    }

    @GetMapping("/getGarnish")
    public @ResponseBody
    Garnish getGarnish(@RequestParam int id) {
        return garnishService.getGarnish(id);
    }

    @PostMapping("/addGarnish")
    public @ResponseBody
    Garnish addGarnish(@RequestBody Garnish garnish) {
        return garnishService.addGarnish(garnish);
    }

    @PutMapping("/updateGarnish")
    public @ResponseBody
    String updateGarnish(@RequestBody Garnish garnish) {
        return garnishService.updateGarnish(garnish);
    }

    @DeleteMapping("/deleteGarnish")
    public @ResponseBody
    String deleteGarnish(@RequestParam int id) {
        return garnishService.deleteGarnish(id);
    }
}
