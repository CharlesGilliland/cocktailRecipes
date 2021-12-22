package com.tsi.training.gilliland.charlie.cocktailRecipes.glassTests;

import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.Glass;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.GlassRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.GlassService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        // Creating a new glass instance
        Glass testGlass = new Glass();
        testGlass.setType("Pint");
        testGlass.setVolume(568);

        // Defining the method call and the return type
        when(glassRepository.findById(testGlass.getId())).thenReturn(Optional.of(testGlass));

        // Recording the result we expect
        Glass expectedGlass = glassService.getGlass(testGlass.getId());

        // Asserting that the expected and actual results are the same
        Assertions.assertEquals(expectedGlass, testGlass);

        // Verifying that the findById method was invoked on the mock
        verify(glassRepository).findById(testGlass.getId());
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
        glass.setType("Original");
        glass.setVolume(200);

        // Defining the method call in the updateGlass method and its return type
        given(glassRepository.findById(glass.getId())).willReturn(Optional.of(glass));

        // Updating the type of the glass
        glass.setType("Updated");

        // Capturing the actual and expected results
        String actual = glassService.updateGlass(glass);
        String expected = "Glass Updated";

        verify(glassRepository).save(glass);
        verify(glassRepository).findById(glass.getId());
        Assertions.assertEquals(expected, actual);

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

}
