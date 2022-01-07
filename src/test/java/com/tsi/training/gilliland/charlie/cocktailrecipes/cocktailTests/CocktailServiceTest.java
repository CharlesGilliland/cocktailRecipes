package com.tsi.training.gilliland.charlie.cocktailrecipes.cocktailTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.CocktailRecipesApplication;
import com.tsi.training.gilliland.charlie.cocktailrecipes.CocktailRecipesApplicationTests;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailService;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.InstructionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;


public class CocktailServiceTest  {

    @BeforeMethod
    void setUp() {
        cocktailService = new CocktailService(cocktailRepository, instructionRepository);
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private CocktailRepository cocktailRepository;

    @Mock
    private InstructionRepository instructionRepository;

    @InjectMocks
    private CocktailService cocktailService;

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
    void testAddCocktailNullName(){
        Cocktail cocktail = new Cocktail();
        cocktail.addInstruction(new Instruction());
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            cocktailService.addCocktail(cocktail);
        });
        String expected = "Please provide a name for the cocktail";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddCocktailEmptyName(){
        Cocktail cocktail = new Cocktail();
        cocktail.setName("");
        cocktail.addInstruction(new Instruction());
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            cocktailService.addCocktail(cocktail);
        });
        String expected = "Please provide a name for the cocktail";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddCocktailNoInstructions(){
        Cocktail cocktail = new Cocktail();
        cocktail.setName("Tester");
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            cocktailService.addCocktail(cocktail);
        });
        String expected = "Please provide instructions for the cocktail";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
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

    @Test
    void testUpdateCocktailNotFound() {
        Cocktail cocktail = new Cocktail();
        cocktail.setName("Tester");
        cocktail.addInstruction(new Instruction());
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            cocktailService.updateCocktail(cocktail);
        });
        String expected = "No cocktail could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDeleteCocktail() {
        Cocktail cocktail = new Cocktail();
        cocktail.setName("Tester");
        cocktail.addInstruction(new Instruction());
        given(cocktailRepository.findById(cocktail.getId())).willReturn(Optional.of(cocktail));
        String expected = "Cocktail Deleted";
        String actual = cocktailService.deleteCocktail(cocktail.getId());
        Assertions.assertEquals(expected, actual);
        verify(cocktailRepository).deleteById(cocktail.getId());
    }

    @Test
    void testDeleteCocktailNotFound() {
        Cocktail cocktail = new Cocktail();
        cocktail.setName("Tester");
        cocktail.addInstruction(new Instruction());
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
           cocktailService.deleteCocktail(cocktail.getId());
        });
        String expected = "No cocktail could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
