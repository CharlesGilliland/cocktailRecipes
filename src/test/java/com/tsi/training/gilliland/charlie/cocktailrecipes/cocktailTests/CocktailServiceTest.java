package com.tsi.training.gilliland.charlie.cocktailrecipes.cocktailTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailService;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.InstructionRepository;
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

    @Mock
    private InstructionRepository instructionRepository;

    private CocktailService cocktailService;

    @BeforeEach
    void setUp() {
        cocktailService = new CocktailService(cocktailRepository, instructionRepository);
    }

    @Test
    void testGetAll() {
        cocktailService.getAll();
        verify(cocktailRepository).findAll();
    }

    @Test
    void testGetCocktail() {
        Cocktail cocktail = new Cocktail();
        given(cocktailRepository.findById(cocktail.getId())).willReturn(Optional.of(cocktail));
        Cocktail actual = cocktailService.getCocktail(cocktail.getId());
        Assertions.assertEquals(cocktail, actual);
        verify(cocktailRepository).findById(cocktail.getId());
    }

    @Test
    void testGetCocktailNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            cocktailService.getCocktail(anyInt());
        });
        String expected = "No cocktail could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddCocktail() {
        Cocktail cocktail = new Cocktail();
        cocktail.setName("Tester");
        cocktail.addInstruction(new Instruction());
        cocktailService.addCocktail(cocktail);
        ArgumentCaptor<Cocktail> cocktailArgumentCaptor = ArgumentCaptor.forClass(Cocktail.class);
        verify(cocktailRepository).save(cocktailArgumentCaptor.capture());
        Cocktail capturedCocktail = cocktailArgumentCaptor.getValue();
        Assertions.assertEquals(cocktail, capturedCocktail);
    }

    @Test
    void testUpdateCocktail() {
        Cocktail cocktail = new Cocktail();
        cocktail.setName("Tester");
        cocktail.addInstruction(new Instruction());
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