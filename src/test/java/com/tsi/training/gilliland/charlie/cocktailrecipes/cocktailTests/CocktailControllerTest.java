package com.tsi.training.gilliland.charlie.cocktailrecipes.cocktailTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.cocktail.CocktailService;
import com.tsi.training.gilliland.charlie.cocktailrecipes.instruction.Instruction;
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
@ContextConfiguration(classes = CocktailController.class)
@WebAppConfiguration
public class CocktailControllerTest {
    @Mock
    private CocktailService  cocktailService;

    @InjectMocks
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
    void testGetAllEmpty() throws Exception {
        when(cocktailService.getAll()).thenReturn(new ArrayList<Cocktail>());
        mockMvc.perform(get("/cocktail/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    void testGetAllWithCocktail() throws Exception {
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
    void testGetCocktail() throws Exception {
        Cocktail cocktail = createCocktailHelper("White Russian");
        when(cocktailService.getCocktail(cocktail.getId())).thenReturn(cocktail);
        mockMvc.perform(get("/cocktail/getCocktail?id=" + cocktail.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":0,\"instructions\":[{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}],\"name\":\"White Russian\",\"description\":null,\"noOfSteps\":0}")));
    }

    @Test
    void testAddCocktail() throws Exception {
        mockMvc.perform(post("/cocktail/addCocktail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"instructions\":[{\"id\":0,\"ingredients\":[],\"equipment\":[],\"glasses\":[],\"garnish\":[],\"description\":null}],\"name\":\"White Russian\",\"description\":null,\"noOfSteps\":0}"))
                .andExpect(status().isOk());
        verify(cocktailService).addCocktail(any(Cocktail.class));
    }

    @Test
    void testUpdateCocktail() throws Exception {
        mockMvc.perform(put("/cocktail/updateCocktail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCocktailHelper("White Russian").toString()))
                .andExpect(status().isOk());
        verify(cocktailService).updateCocktail(any(Cocktail.class));
    }

    @Test
    void testDeleteCocktail() throws Exception {
        when(cocktailService.deleteCocktail(2)).thenReturn("Cocktail Deleted");
        mockMvc.perform(delete("/cocktail/deleteCocktail?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Cocktail Deleted")));
        verify(cocktailService).deleteCocktail(2);
    }
}
