package com.tsi.training.gilliland.charlie.cocktailRecipes;

import com.tsi.training.gilliland.charlie.cocktailRecipes.models.PrettyJsonHelper;
import com.tsi.training.gilliland.charlie.cocktailRecipes.models.TestJsonResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CocktailRecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailRecipesApplication.class, args);
	}
	
	@GetMapping("/getCocktail")
	public String cocktailResponce(){
		String result = TestJsonResult.createWhiskeyAndCoke();
		return result;
	}

}
