package com.tsi.training.gilliland.charlie.cocktailRecipes.glassTests;

import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.Glass;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.GlassRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.GlassService;
import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.Ingredient;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GlassServiceTest {

    @Mock
    private GlassRepository glassRepository;

    private GlassService glassService;

    @BeforeEach
    void setUp() {
        glassService = new GlassService(glassRepository);
    }

    @Test
    public void testGetGlasses() {
        glassService.getGlasses();
        verify(glassRepository).findAll();
    }

    @Test
    public void testGetGlass() {
        Glass glass = new Glass();
        glass.setType("Pint");
        glass.setVolume(568);

        // Setting conditions of the test
        given(glassRepository.findById(glass.getId())).willReturn(Optional.of(glass));

        // Setting the expected value
        Glass expected = glassService.getGlass(glass.getId());

        // Asserting the two values are equal
        Assertions.assertEquals(expected, glass);

        // Verifying that the findById method was called
        verify(glassRepository, atLeastOnce()).findById(glass.getId());
    }

    @Test
    public void testGetGlassNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            glassService.getGlass(anyInt());
        });
        String expected = "No glass could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAddGlass() {
        // Creating test object
        Glass glass = new Glass();
        glass.setType("Pint");
        glass.setVolume(568);

        // Setting the expected return string
        String expected = "Saved";

        // Adding object to the repo and capturing return value
        String actual = glassService.addGlass(glass);

        // Creating an argument captor
        ArgumentCaptor<Glass> glassArgumentCaptor = ArgumentCaptor.forClass(Glass.class);

        // Verifying that save() was called on the repo
        verify(glassRepository).save(glassArgumentCaptor.capture());

        // Getting the captured value
        Glass capturedGlass = glassArgumentCaptor.getValue();

        // Asserting the captured value is the same as the original object
        Assertions.assertEquals(glass, capturedGlass);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testUpdateGlass() {
        Glass glass = new Glass();

        // Defining the method call in the updateGlass method and its return type
        given(glassRepository.findById(glass.getId())).willReturn(Optional.of(glass));

        // Adding glass to repo
        glassService.addGlass(glass);

        // Updating the type of the glass
        glass.setType("Updated");

        // Creating argument captor
        ArgumentCaptor<Glass> glassArgumentCaptor = ArgumentCaptor.forClass(Glass.class);

        // Capturing the actual and expected results
        String actual = glassService.updateGlass(glass);
        String expected = "Glass Updated";

        // Verifying if the save method has been called at least twice (initial save then update)
        verify(glassRepository).save(glassArgumentCaptor.capture());
        Glass capturedGlass = glassArgumentCaptor.getValue();

        // Verifying if findById has been called once
        verify(glassRepository).findById(glass.getId());

        // Asserting the values are as expected
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(glass, capturedGlass);

    }

    @Test
    public void testUpdatedGlassNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            glassService.updateGlass(new Glass());
        });
        String expected = "No glass could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    // TODO - finish the tests for here ie DELETE

}
