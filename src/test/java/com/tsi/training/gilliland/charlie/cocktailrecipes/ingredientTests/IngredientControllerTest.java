package com.tsi.training.gilliland.charlie.cocktailrecipes.ingredientTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.ingredient.Ingredient;
import com.tsi.training.gilliland.charlie.cocktailrecipes.ingredient.IngredientController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.ingredient.IngredientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(IngredientController.class)
public class IngredientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IngredientService ingredientService;

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
    public void testGetAllEmpty() throws Exception {
        when(ingredientService.getIngredients()).thenReturn(new ArrayList<Ingredient>());
        mockMvc.perform(get("/ingredient/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void testGetAllWithIngredient() throws Exception {
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
    public void testGetIngredient() throws Exception {
        Ingredient ingredient = createIngredientHelper("Kraken", "Rum", 40, "ambient", "Kraken rum.");
        when(ingredientService.getIngredient(ingredient.getId())).thenReturn(ingredient);
        mockMvc.perform(get("/ingredient/getIngredient?id=" + ingredient.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":0,\"name\":\"Kraken\",\"type\":\"Rum\",\"abv\":40.0,\"storage\":\"ambient\",\"description\":\"Kraken rum.\"}")));
    }

    @Test
    public void testAddIngredient() throws Exception {
        mockMvc.perform(post("/ingredient/addIngredient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"name\":\"Kraken\",\"type\":\"Rum\",\"abv\":40.0,\"storage\":\"ambient\",\"description\":\"Kraken rum.\"}"))
                .andExpect(status().isOk());
        verify(ingredientService).addIngredient(any(Ingredient.class));
    }

    @Test
    public void testUpdateIngredient() throws Exception {
        mockMvc.perform(put("/ingredient/updateIngredient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createIngredientHelper("Kraken", "Rum", 40, "ambient", "Kraken rum.").toString()))
                .andExpect(status().isOk());
        verify(ingredientService).updateIngredient(any(Ingredient.class));
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        when(ingredientService.deleteIngredient(2)).thenReturn("Ingredient Deleted");
        mockMvc.perform(delete("/ingredient/deleteIngredient?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Ingredient Deleted")));
        verify(ingredientService).deleteIngredient(2);
    }
}
