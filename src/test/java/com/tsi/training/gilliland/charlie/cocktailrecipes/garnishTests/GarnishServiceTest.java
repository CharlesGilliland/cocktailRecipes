package com.tsi.training.gilliland.charlie.cocktailrecipes.garnishTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.GarnishRepository;
import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.GarnishService;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GarnishServiceTest {

    @Mock
    private GarnishRepository garnishRepository;

    @InjectMocks
    private GarnishService garnishService;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
        garnishService = new GarnishService(garnishRepository);
    }

    @Test
    void testGetAllGarnish() {
        garnishService.getAllGarnish();
        verify(garnishRepository).findAll();
    }

    @Test
    void testGetGarnish() {
        Garnish garnish = new Garnish();
        garnish.setType("Umbrella");
        garnish.setStorage("Ambient");

        // Setting conditions of the test
        given(garnishRepository.findById(garnish.getId())).willReturn(Optional.of(garnish));

        // Setting the expected value
        Garnish expected = garnishService.getGarnish(garnish.getId());

        // Asserting the two values are equal
        Assertions.assertEquals(expected, garnish);

        // Verifying that the findById method was called
        verify(garnishRepository).findById(garnish.getId());
    }

    @Test
    void testGetGarnishNotFound() {
        // Calling the method with an invalid ID
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            garnishService.getGarnish(anyInt());
        });
        // Capturing te expected and actual values
        String expected = "No garnish could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddGarnish() {
        // Creating test object
        Garnish garnish = new Garnish();
        garnish.setType("Umbrella");
        garnish.setStorage("Ambient");

        garnishService.addGarnish(garnish);

        // Creating argument captor
        ArgumentCaptor<Garnish> garnishArgumentCaptor = ArgumentCaptor.forClass(Garnish.class);

        // Verifying the save method was called on the mock
        verify(garnishRepository).save(garnishArgumentCaptor.capture());

        // Getting the captured value
        Garnish capturedGarnish = garnishArgumentCaptor.getValue();

        // Asserting the garnish objects and string are equal
        Assertions.assertEquals(garnish, capturedGarnish);
    }

    @Test
    void testAddGarnishNullType() {
        Garnish garnish = new Garnish();
        garnish.setStorage("Ambient");
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            garnishService.addGarnish(garnish);
        });
        String expected = "Please supply a type for the garnish";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddGarnishEmptyType() {
        Garnish garnish = new Garnish();
        garnish.setStorage("Ambient");
        garnish.setType("");
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            garnishService.addGarnish(garnish);
        });
        String expected = "Please supply a type for the garnish";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateGarnish() {
        Garnish garnish = new Garnish();
        garnish.setType("Umbrella");
        garnish.setStorage("Ambient");

        // Defining the call in the updateGarnish method and its return
        given(garnishRepository.findById(garnish.getId())).willReturn(Optional.of(garnish));

        // Updating the type of the garnish
        garnish.setType("Salt");

        // Capturing the actual and expected results
        ArgumentCaptor<Garnish> garnishArgumentCaptor = ArgumentCaptor.forClass(Garnish.class);
        String expected = "Garnish updated";
        String actual = garnishService.updateGarnish(garnish);

        // Verifying method has been called and capturing its result
        verify(garnishRepository).save(garnishArgumentCaptor.capture());

        // Getting the captured value
        Garnish capturedGarnish = garnishArgumentCaptor.getValue();

        // Asserting the garnish object and strings are equal
        Assertions.assertEquals(garnish, capturedGarnish);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateGarnishNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            garnishService.updateGarnish(new Garnish());
        });
        String expected = "No garnish could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDeleteGarnish() {
        Garnish garnish = new Garnish();

        // Defining what the findById method will use and what will be returned
        given(garnishRepository.findById(garnish.getId())).willReturn(Optional.of(garnish));

        // Setting actual vs expected results
        String expected = "Garnish deleted";
        String actual = garnishService.deleteGarnish(garnish.getId());

        // Asserting the returned strings are equal and that deleteById has been called on the repo
        Assertions.assertEquals(expected, actual);
        verify(garnishRepository).deleteById(garnish.getId());
    }

    @Test
    void testDeleteGarnishNotFound() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            garnishService.deleteGarnish(anyInt());
        });
        String expected = "No garnish could be found with the given ID";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
