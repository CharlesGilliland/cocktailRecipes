package com.tsi.training.gilliland.charlie.cocktailrecipes.garnish;

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

    String noGarnishWithId = "No garnish could be found with the given ID";

    @Autowired
    GarnishRepository garnishRepository;

    public List<Garnish> getAllGarnish() {
        return garnishRepository.findAll();
    }

    public Garnish getGarnish(int id) {
        Optional<Garnish> garnishOptional = garnishRepository.findById(id);
        if(garnishOptional.isEmpty()){
            throw new NoSuchElementException(noGarnishWithId);
        }
        return garnishOptional.get();
    }

    public Garnish addGarnish(Garnish garnish) {
        if(garnish.getType() == null || garnish.getType().isEmpty()){
            throw new IllegalArgumentException("Please supply a type for the garnish");
        }
        return garnishRepository.save(garnish);
    }

    public String updateGarnish(Garnish garnish) {
        Optional<Garnish> garnishInDbOptional = garnishRepository.findById(garnish.getId());
        if (garnishInDbOptional.isEmpty()) {
            throw new NoSuchElementException(noGarnishWithId);
        }
        garnishRepository.save(garnish);
        return "Garnish updated";
    }

    public String deleteGarnish(int id) {
        Optional<Garnish> garnishInDb = garnishRepository.findById(id);
        if (garnishInDb.isEmpty()) {
            throw new NoSuchElementException(noGarnishWithId);
        }
        garnishRepository.deleteById(id);
        return "Garnish deleted";
    }
}
