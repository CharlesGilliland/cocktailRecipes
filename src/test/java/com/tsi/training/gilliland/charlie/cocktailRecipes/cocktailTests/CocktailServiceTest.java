package com.tsi.training.gilliland.charlie.cocktailRecipes.cocktailTests;

import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.CocktailRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.CocktailService;
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
public class CocktailServiceTest {

    @Mock
    private CocktailRepository cocktailRepository;

    private CocktailService cocktailService;

    @BeforeEach
    void setUp() {
        cocktailService = new CocktailService(cocktailRepository);
    }

    @Test
    public void testGetAll() {
        cocktailService.getAll();
        verify(cocktailRepository).findAll();
    }

    @Test
    public void testGetCocktail() {
        Cocktail cocktail = new Cocktail();
        given(cocktailRepository.findById(cocktail.getId())).willReturn(Optional.of(cocktail));
        Cocktail actual = cocktailService.getCocktail(cocktail.getId());
        Assertions.assertEquals(cocktail, actual);
        verify(cocktailRepository).findById(cocktail.getId());
    }

    @Test
    public void testGetCocktailNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            cocktailService.getCocktail(anyInt());
        });
        String expected = "No cocktail could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAddCocktail() {
        Cocktail cocktail = new Cocktail();
        String expected = "Saved";
        String actual = cocktailService.addCocktail(cocktail);
        ArgumentCaptor<Cocktail> cocktailArgumentCaptor = ArgumentCaptor.forClass(Cocktail.class);
        verify(cocktailRepository).save(cocktailArgumentCaptor.capture());
        Cocktail capturedCocktail = cocktailArgumentCaptor.getValue();
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(cocktail, capturedCocktail);
    }

    @Test
    public void testUpdateCocktail() {
        Cocktail cocktail = new Cocktail();
        given(cocktailRepository.findById(cocktail.getId())).willReturn(Optional.of(cocktail));
        cocktailService.addCocktail(cocktail);
        cocktail.setDescription("Updated description");
        ArgumentCaptor<Cocktail> cocktailArgumentCaptor = ArgumentCaptor.forClass(Cocktail.class);
        String expected = "Cocktail Updated";
        String actual = cocktailService.updateCocktail(cocktail);
        verify(cocktailRepository, atLeast(2)).save(cocktailArgumentCaptor.capture());
        verify(cocktailRepository).findById(cocktail.getId());
        Cocktail capturedCocktail = cocktailArgumentCaptor.getValue();
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(cocktail, capturedCocktail);
    }
}
