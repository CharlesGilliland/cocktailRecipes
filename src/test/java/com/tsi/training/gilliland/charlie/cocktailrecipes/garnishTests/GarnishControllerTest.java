package com.tsi.training.gilliland.charlie.cocktailrecipes.garnishTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.GarnishController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.GarnishService;
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
@ContextConfiguration(classes = GarnishController.class)
@WebAppConfiguration
public class GarnishControllerTest {
    @Mock
    GarnishService garnishService;

    @Mock
    GarnishController garnishController = new GarnishController();

    private MockMvc mockMvc;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(garnishController).build();
    }

    private static Garnish createGarnishHelper(String type, String storage) {
        Garnish garnish = new Garnish();
        garnish.setType(type);
        garnish.setStorage(storage);
        return garnish;
    }

    @Test
    void testGetAllEmpty() throws Exception {
        when(garnishService.getAllGarnish()).thenReturn(new ArrayList<Garnish>());
        mockMvc.perform(get("/garnish/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetGarnish() throws Exception {
        Garnish garnish = createGarnishHelper("Tester", "Testing");
        mockMvc.perform(get("/garnish/getGarnish?id=" + garnish.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void testAddGlass() throws Exception {
        mockMvc.perform(post("/garnish/addGarnish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"Pint\",\"volume\":568}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateGarnish() throws Exception {
        mockMvc.perform(put("/garnish/updateGarnish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createGarnishHelper("Tester", "Testing").toString()))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteGarnish() throws Exception {
        mockMvc.perform(delete("/garnish/deleteGarnish?id=2"))
                .andExpect(status().isOk());
    }


}
