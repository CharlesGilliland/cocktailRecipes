package com.tsi.training.gilliland.charlie.cocktailRecipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class CocktailRecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailRecipesApplication.class, args);
	}

	@GetMapping("/getCocktail")
	public String cocktailResponce(){
		return Main.createWhiskeyAndCoke();
	}

}
