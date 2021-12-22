package com.tsi.training.gilliland.charlie.cocktailRecipes.garnish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GarnishService {

    public GarnishService(GarnishRepository garnishRepository) {
        this.garnishRepository = garnishRepository;
    }

    @Autowired
    GarnishRepository garnishRepository;

    public List<Garnish> getAllGarnish() {
        return garnishRepository.findAll();
    }

    public Garnish getGarnish(int id) {
        Optional<Garnish> garnishOptional = garnishRepository.findById(id);
        if(garnishOptional.isEmpty()){
            throw new NoSuchElementException("No garnish could be found with the given ID");
        }
        return garnishOptional.get();
    }

    public String addGarnish(Garnish garnish) {
        try {
            garnishRepository.save(garnish);
        } catch (Exception e){
            return e.getMessage();
        }
        return "Saved";
    }

    public String updateGarnish(Garnish garnish) {
        Optional<Garnish> garnishInDbOptional = garnishRepository.findById(garnish.getId());
        if (garnishInDbOptional.isEmpty()) {
            throw new NoSuchElementException("No garnish could be found with the given ID");
        }
        garnishRepository.save(garnish);
        return "Garnish updated";
    }

    public String deleteGarnish(int id) {
        Optional<Garnish> garnishInDb = garnishRepository.findById(id);
        if (garnishInDb.isEmpty()) {
            throw new NoSuchElementException("No garnish could be found with the given ID");
        }
        garnishRepository.deleteById(id);
        return "Garnish deleted";
    }
}
