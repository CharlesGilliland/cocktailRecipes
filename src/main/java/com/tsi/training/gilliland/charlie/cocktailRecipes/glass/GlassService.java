package com.tsi.training.gilliland.charlie.cocktailRecipes.glass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GlassService {

    public GlassService(GlassRepository glassRepository){
        this.glassRepository = glassRepository;
    }

    @Autowired
    private GlassRepository glassRepository;

    public List<Glass> getGlasses() {
        return glassRepository.findAll();
    }

    public Glass getGlass(int id) {
        return glassRepository.findById(id).get();
    }

    public String addGlass(Glass glass) {
        try {
            glassRepository.save(glass);
        } catch (Exception e){
            return e.getMessage();
        }
        return "Saved";
    }

    public String updateGlass(Glass glass) {
        Optional<Glass> glassInDb = glassRepository.findById(glass.getId());
        if (glassInDb.isEmpty()) {
            return "Glass is not in database";
        }
        glassRepository.save(glass);
        return "Glass Updated";
    }

    public String deleteGlass(int id) {
        Optional<Glass> glassInDb = glassRepository.findById(id);
        if (glassInDb.isEmpty()) {
            return "Glass not found";
        }
        glassRepository.deleteById(id);
        return "Glass Deleted";
    }

}
