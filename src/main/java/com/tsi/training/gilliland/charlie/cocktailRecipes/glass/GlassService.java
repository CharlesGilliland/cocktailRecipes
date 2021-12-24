package com.tsi.training.gilliland.charlie.cocktailRecipes.glass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        Optional<Glass> glassOptional = glassRepository.findById(id);
        if(glassOptional.isEmpty()){
            throw new NoSuchElementException("No glass could be found with the given ID");
        }
        return glassRepository.findById(id).get();
    }

    public String addGlass(Glass glass) {
        if(glass.getType() == null) {
            return "Please provide a type for the glass";
        }
        if(glass.getVolume() <= 0) {
            return "Please provide a volume for the glass";
        }
        glassRepository.save(glass);

        return "Saved";
    }

    public String updateGlass(Glass glass) {
        Optional<Glass> glassInDb = glassRepository.findById(glass.getId());
        if (glassInDb.isEmpty()) {
            throw new NoSuchElementException("No glass could be found with the given ID");
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
