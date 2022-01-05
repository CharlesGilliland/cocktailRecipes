package com.tsi.training.gilliland.charlie.cocktailrecipes.glass;

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

    String noGlassWithId = "No glass could be found with the given ID";

    @Autowired
    private GlassRepository glassRepository;

    public List<Glass> getGlasses() {
        return glassRepository.findAll();
    }

    public Glass getGlass(int id) {
        Optional<Glass> glassOptional = glassRepository.findById(id);
        if(glassOptional.isEmpty()){
            throw new NoSuchElementException(noGlassWithId);
        }
        return glassOptional.get();
    }

    public Glass addGlass(Glass glass) {
        if(glass.getType() == null || glass.getType().equals("")) {
            throw new IllegalArgumentException("Please provide a type for the glass");
        }
        if(glass.getVolume() <= 0) {
            throw new IllegalArgumentException("Please provide a volume for the glass");
        }
        return glassRepository.save(glass);
    }

    public String updateGlass(Glass glass) {
        Optional<Glass> glassInDb = glassRepository.findById(glass.getId());
        if (glassInDb.isEmpty()) {
            throw new NoSuchElementException(noGlassWithId);
        }
        glassRepository.save(glass);
        return "Glass Updated";
    }

    public String deleteGlass(int id) {
        Optional<Glass> glassInDb = glassRepository.findById(id);
        if (glassInDb.isEmpty()) {
            throw new NoSuchElementException(noGlassWithId);
        }
        glassRepository.deleteById(id);
        return "Glass Deleted";
    }

}
