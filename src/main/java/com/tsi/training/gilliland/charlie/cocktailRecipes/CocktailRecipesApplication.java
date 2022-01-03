package com.tsi.training.gilliland.charlie.cocktailRecipes;
 
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
@RequestMapping("/cocktails")
public class CocktailRecipesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CocktailRecipesApplication.class, args);
    }
}