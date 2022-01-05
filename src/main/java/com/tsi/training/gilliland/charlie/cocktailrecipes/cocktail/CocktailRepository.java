package com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
}
