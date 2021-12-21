package com.tsi.training.gilliland.charlie.cocktailRecipes.glassTests;

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

    }

    @Test
    public void testAddGlass() {
        // Creating test object
        Glass glass = new Glass();
        glass.setType("Pint");
        glass.setVolume(568);

        // Adding object to the repo
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

}
