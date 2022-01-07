package com.tsi.training.gilliland.charlie.cocktailrecipes.ingredientTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.ingredient.Ingredient;
import com.tsi.training.gilliland.charlie.cocktailrecipes.ingredient.IngredientController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.ingredient.IngredientService;
import org.mockito.InjectMocks;
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
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = IngredientController.class)
@WebAppConfiguration
public class IngredientControllerTest {
    @Mock
    IngredientService ingredientService;

    @InjectMocks
    IngredientController ingredientController = new IngredientController();

    private MockMvc mockMvc;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    private static Ingredient createIngredientHelper(String name, String type, float abv, String storage, String description) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setType(type);
        ingredient.setAbv(abv);
        ingredient.setStorage(storage);
        ingredient.setDescription(description);
        return ingredient;
    }

    @Test
    void testGetAllEmpty() throws Exception {
        when(ingredientService.getIngredients()).thenReturn(new ArrayList<Ingredient>());
        mockMvc.perform(get("/ingredient/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    void testGetAllWithIngredient() throws Exception {
        Ingredient ingredient1 = createIngredientHelper("Kraken", "Rum", 40, "ambient", "Kraken rum.");
        Ingredient ingredient2 = createIngredientHelper("Smirnoff", "Vodka", 37.5f, "ambient", "Smirnoff vodka.");
        List<Ingredient> ingredientList = new ArrayList<Ingredient>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        when(ingredientService.getIngredients()).thenReturn(ingredientList);
        mockMvc.perform(get("/ingredient/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":0,\"name\":\"Kraken\",\"type\":\"Rum\",\"abv\":40.0,\"storage\":\"ambient\",\"description\":\"Kraken rum.\"},{\"id\":0,\"name\":\"Smirnoff\",\"type\":\"Vodka\",\"abv\":37.5,\"storage\":\"ambient\",\"description\":\"Smirnoff vodka.\"}]")));
    }

    @Test
    void testGetIngredient() throws Exception {
        Ingredient ingredient = createIngredientHelper("Kraken", "Rum", 40, "ambient", "Kraken rum.");
        when(ingredientService.getIngredient(ingredient.getId())).thenReturn(ingredient);
        mockMvc.perform(get("/ingredient/getIngredient?id=" + ingredient.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":0,\"name\":\"Kraken\",\"type\":\"Rum\",\"abv\":40.0,\"storage\":\"ambient\",\"description\":\"Kraken rum.\"}")));
    }

    @Test
    void testAddIngredient() throws Exception {
        mockMvc.perform(post("/ingredient/addIngredient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"name\":\"Kraken\",\"type\":\"Rum\",\"abv\":40.0,\"storage\":\"ambient\",\"description\":\"Kraken rum.\"}"))
                .andExpect(status().isOk());
        verify(ingredientService).addIngredient(any(Ingredient.class));
    }

    @Test
    void testUpdateIngredient() throws Exception {
        mockMvc.perform(put("/ingredient/updateIngredient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createIngredientHelper("Kraken", "Rum", 40, "ambient", "Kraken rum.").toString()))
                .andExpect(status().isOk());
        verify(ingredientService).updateIngredient(any(Ingredient.class));
    }

    @Test
    void testDeleteIngredient() throws Exception {
        when(ingredientService.deleteIngredient(2)).thenReturn("Ingredient Deleted");
        mockMvc.perform(delete("/ingredient/deleteIngredient?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Ingredient Deleted")));
        verify(ingredientService).deleteIngredient(2);
    }
}
