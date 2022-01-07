package com.tsi.training.gilliland.charlie.cocktailrecipes.glassTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.Glass;
import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.GlassController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.GlassService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = GlassController.class)
@WebAppConfiguration
public class GlassControllerTest {
    @Mock
    GlassService glassService;

    @Mock
    GlassController glassController = new GlassController();

    private MockMvc mockMvc;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(glassController).build();
    }
    private static Glass createGlassHelper(String type, int volume) {
        Glass glass = new Glass();
        glass.setType(type);
        glass.setVolume(volume);
        return glass;
    }

    @Test
    void testGetAllEmpty() throws Exception {
        when(glassService.getGlasses()).thenReturn(new ArrayList<Glass>());
        mockMvc.perform(get("/glass/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetGlass() throws Exception {
        Glass glass = createGlassHelper("Pint", 568);
        when(glassService.getGlass(glass.getId())).thenReturn(glass);
        mockMvc.perform(get("/glass/getGlass?id=" + glass.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void testAddGlass() throws Exception {
        mockMvc.perform(post("/glass/addGlass")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"type\":\"Pint\",\"volume\":568}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateGlass() throws Exception {
        mockMvc.perform(put("/glass/updateGlass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createGlassHelper("Test", 568).toString()))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteGlass() throws Exception {
        when(glassService.deleteGlass(2)).thenReturn("Glass Deleted");
        mockMvc.perform(delete("/glass/deleteGlass?id=2"))
                .andExpect(status().isOk());
    }
}
