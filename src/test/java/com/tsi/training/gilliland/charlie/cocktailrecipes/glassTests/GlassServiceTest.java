package com.tsi.training.gilliland.charlie.cocktailrecipes.glassTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.Glass;
import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.GlassRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.GlassService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GlassServiceTest {

    @Mock
    private GlassRepository glassRepository;

    @InjectMocks
    private GlassService glassService;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
        glassService = new GlassService(glassRepository);
    }

    @Test
    void testGetGlasses() {
        glassService.getGlasses();
        verify(glassRepository).findAll();
    }

    @Test
    void testGetGlass() {
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
    void testGetGlassNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            glassService.getGlass(anyInt());
        });
        String expected = "No glass could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddGlass() {
        // Creating test object
        Glass glass = new Glass();
        glass.setType("Pint");
        glass.setVolume(568);

        // Adding object to the repo and capturing return value
        glassService.addGlass(glass);

        // Creating an argument captor
        ArgumentCaptor<Glass> glassArgumentCaptor = ArgumentCaptor.forClass(Glass.class);

        // Verifying that save() was called on the repo
        verify(glassRepository).save(glassArgumentCaptor.capture());

        // Getting the captured value
        Glass capturedGlass = glassArgumentCaptor.getValue();

        // Asserting the captured value is the same as the original object
        Assertions.assertEquals(glass, capturedGlass);
    }

    @Test
    void testAddGlassNullType() {
        Glass glass = new Glass();
        glass.setVolume(568);
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            glassService.addGlass(glass);
        });
        String expected = "Please provide a type for the glass";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddGlassEmptyType() {
        Glass glass = new Glass();
        glass.setType("");
        glass.setVolume(568);
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            glassService.addGlass(glass);
        });
        String expected = "Please provide a type for the glass";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddGlassNoVolume() {
        Glass glass = new Glass();
        glass.setType("Pint");
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            glassService.addGlass(glass);
        });
        String expected = "Please provide a volume for the glass";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateGlass() {
        Glass glass = new Glass();
        glass.setType("Tester");
        glass.setVolume(200);

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
        verify(glassRepository,atLeast(2)).save(glassArgumentCaptor.capture());
        Glass capturedGlass = glassArgumentCaptor.getValue();

        // Verifying if findById has been called once
        verify(glassRepository).findById(glass.getId());

        // Asserting the values are as expected
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(glass, capturedGlass);
    }

    @Test
    void testUpdatedGlassNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            glassService.updateGlass(new Glass());
        });
        String expected = "No glass could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDeleteGlass(){
        Glass glass = new Glass();

        // Defining what the findById method will use and what will be returned
        given(glassRepository.findById(glass.getId())).willReturn(Optional.of(glass));

        // Setting actual vs expected results
        String expected = "Glass Deleted";
        String actual = glassService.deleteGlass(glass.getId());

        // Asserting the returned strings are equal and that deleteById has been called on the repo
        Assertions.assertEquals(expected, actual);
        verify(glassRepository).deleteById(glass.getId());
    }

    @Test
    void testDeleteGlassNotFound(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            glassService.deleteGlass(anyInt());
        });
        String expected = "No glass could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
