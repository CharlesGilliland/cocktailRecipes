package com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
}
