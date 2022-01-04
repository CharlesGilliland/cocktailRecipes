package com.tsi.training.gilliland.charlie.cocktailRecipes.ingredientTests;

import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.Ingredient;
import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.IngredientRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.IngredientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    private IngredientService ingredientService;

    @BeforeEach
    void setup() {
        ingredientService = new IngredientService(ingredientRepository);
    }

    @Test
    public void testGetIngredients() {
        ingredientService.getIngredients();
        verify(ingredientRepository).findAll();
    }

    @Test
    public void testGetIngredient() {
        Ingredient ingredient = new Ingredient();

        // Setting conditions of the test
        given(ingredientRepository.findById(ingredient.getId())).willReturn(Optional.of(ingredient));

        // Setting the actual value
        Ingredient actual = ingredientService.getIngredient(ingredient.getId());

        // Asserting the two values are equal
        Assertions.assertEquals(ingredient, actual);

        // Verifying the findById method was called
        verify(ingredientRepository).findById(ingredient.getId());
    }

    @Test
    public void testGetIngredientNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            ingredientService.getIngredient(anyInt());
        });
        String expected = "No ingredient could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAddIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Test");
        ingredient.setType("Test");
        ingredient.setAbv(20);

        ingredientService.addIngredient(ingredient);

        // Setting up argument captor
        ArgumentCaptor<Ingredient> ingredientArgumentCaptor = ArgumentCaptor.forClass(Ingredient.class);
        verify(ingredientRepository).save(ingredientArgumentCaptor.capture());
        Ingredient capturedIngredient = ingredientArgumentCaptor.getValue();

        // Asserting the values are as expected
        Assertions.assertEquals(ingredient, capturedIngredient);
    }

    @Test
    public void testUpdateIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Test");
        ingredient.setType("Test");
        ingredient.setAbv(20);

        // Defining the method call for findById
        given(ingredientRepository.findById(ingredient.getId())).willReturn(Optional.of(ingredient));

        // Adding ingredient
        ingredientService.addIngredient(ingredient);

        // Changing the ingredients type
        ingredient.setType("Vodka");

        // Creating argument captor
        ArgumentCaptor<Ingredient> ingredientArgumentCaptor = ArgumentCaptor.forClass(Ingredient.class);

        // Capturing expected and actual results
        String expected = "Ingredient Updated";
        String actual = ingredientService.updateIngredient(ingredient);

        // Verifying if the save method has been called at least twice (initial save then update)
        verify(ingredientRepository, atLeast(2)).save(ingredientArgumentCaptor.capture());
        Ingredient capturedIngredient = ingredientArgumentCaptor.getValue();

        // Verifying if findById has been called once
        verify(ingredientRepository).findById(ingredient.getId());

        // Asserting the values are as expected
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(ingredient, capturedIngredient);
    }

    @Test
    public void testUpdateIngredientNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            ingredientService.updateIngredient(new Ingredient());
        });
        String expected = "No ingredient could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteIngredient() {
        Ingredient ingredient = new Ingredient();

        given(ingredientRepository.findById(ingredient.getId())).willReturn(Optional.of(ingredient));

        String expected = "Ingredient Deleted";
        String actual = ingredientService.deleteIngredient(ingredient.getId());

        Assertions.assertEquals(expected, actual);
        verify(ingredientRepository).deleteById(ingredient.getId());
    }

    @Test
    public void testDeleteIngredientNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            ingredientService.deleteIngredient(anyInt());
        });
        String expected = "No ingredient could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
