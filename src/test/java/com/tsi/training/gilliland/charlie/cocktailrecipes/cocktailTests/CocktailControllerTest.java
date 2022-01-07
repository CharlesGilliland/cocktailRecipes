package com.tsi.training.gilliland.charlie.cocktailrecipes.cocktailTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailService;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
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

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = CocktailController.class)
@WebAppConfiguration
public class CocktailControllerTest {
    @Mock
    private CocktailService  cocktailService;

    @Mock
    private CocktailController cocktailController = new CocktailController();

    MockMvc mockMvc;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cocktailController).build();
    }

    private static Cocktail createCocktailHelper(String name){
        Cocktail cocktail = new Cocktail();
        Instruction instruction = new Instruction();
        cocktail.addInstruction(instruction);
        cocktail.setName(name);
        return cocktail;
    }

    @Test
    void testGetAll() throws Exception {
        when(cocktailService.getAll()).thenReturn(new ArrayList<Cocktail>());
        mockMvc.perform(get("/cocktail/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }


    @Test
    void testGetCocktail() throws Exception {
        Cocktail cocktail = createCocktailHelper("White Russian");
        mockMvc.perform(get("/cocktail/getCocktail?id=" + cocktail.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void testAddCocktail() throws Exception {
        mockMvc.perform(post("/cocktail/addCocktail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"instructions\":[{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}],\"name\":\"White Russian\",\"description\":null,\"noOfSteps\":0}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCocktail() throws Exception {
        mockMvc.perform(put("/cocktail/updateCocktail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCocktailHelper("White Russian").toString()))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCocktail() throws Exception {
        mockMvc.perform(delete("/cocktail/deleteCocktail?id=2"))
                .andExpect(status().isOk());
    }
}
