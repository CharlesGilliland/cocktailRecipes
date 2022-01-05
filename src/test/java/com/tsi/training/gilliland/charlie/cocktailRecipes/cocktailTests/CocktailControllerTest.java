package com.tsi.training.gilliland.charlie.cocktailRecipes.cocktailTests;

import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.CocktailController;
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.CocktailService;
import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.Instruction;
import io.cucumber.java.bs.I;
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
@WebMvcTest(CocktailController.class)
public class CocktailControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CocktailService cocktailService;

    private static Cocktail createCocktailHelper(String name){
        Cocktail cocktail = new Cocktail();
        Instruction instruction = new Instruction();
        cocktail.addInstruction(instruction);
        cocktail.setName(name);
        return cocktail;
    }

    @Test
    public void testGetAllEmpty() throws Exception {
        when(cocktailService.getAll()).thenReturn(new ArrayList<Cocktail>());
        mockMvc.perform(get("/cocktail/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void testGetAllWithCocktail() throws Exception {
        Cocktail cocktail1 = createCocktailHelper("White Russian");
        Cocktail cocktail2 = createCocktailHelper("Sex on the beach");
        List<Cocktail> cocktailList = new ArrayList<Cocktail>();
        cocktailList.add(cocktail1);
        cocktailList.add(cocktail2);
        when(cocktailService.getAll()).thenReturn(cocktailList);
        mockMvc.perform(get("/cocktail/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":0,\"instructions\":[{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}],\"name\":\"White Russian\",\"description\":null,\"noOfSteps\":0},{\"id\":0,\"instructions\":[{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}],\"name\":\"Sex on the beach\",\"description\":null,\"noOfSteps\":0}]")));
    }

    @Test
    public void testGetCocktail() throws Exception {
        Cocktail cocktail = createCocktailHelper("White Russian");
        when(cocktailService.getCocktail(cocktail.getId())).thenReturn(cocktail);
        mockMvc.perform(get("/cocktail/getCocktail?id=" + cocktail.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":0,\"instructions\":[{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}],\"name\":\"White Russian\",\"description\":null,\"noOfSteps\":0}")));
    }

    @Test
    public void testAddCocktail() throws Exception {
        mockMvc.perform(post("/cocktail/addCocktail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"instructions\":[{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}],\"name\":\"White Russian\",\"description\":null,\"noOfSteps\":0}"))
                .andExpect(status().isOk());
        verify(cocktailService).addCocktail(any(Cocktail.class));
    }

    @Test
    public void testUpdateCocktail() throws Exception {
        mockMvc.perform(put("/cocktail/updateCocktail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCocktailHelper("White Russian").toString()))
                .andExpect(status().isOk());
        verify(cocktailService).updateCocktail(any(Cocktail.class));
    }

    @Test
    public void testDeleteCocktail() throws Exception {
        when(cocktailService.deleteCocktail(2)).thenReturn("Cocktail Deleted");
        mockMvc.perform(delete("/cocktail/deleteCocktail?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Cocktail Deleted")));
        verify(cocktailService).deleteCocktail(2);
    }
}
