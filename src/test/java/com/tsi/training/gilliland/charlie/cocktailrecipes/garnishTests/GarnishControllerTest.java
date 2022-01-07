package com.tsi.training.gilliland.charlie.cocktailrecipes.garnishTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.GarnishController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.GarnishService;
import org.mockito.InjectMocks;
import org.testng.annotations.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;

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
@ContextConfiguration(classes = GarnishController.class)
@WebAppConfiguration
public class GarnishControllerTest {
    @Mock
    GarnishService garnishService;

    @InjectMocks
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
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    void testGetAllWithGarnish() throws Exception {
        Garnish garnish1 = createGarnishHelper("Tester", "Testing");
        Garnish garnish2 = createGarnishHelper("Tester", "Testing");
        List<Garnish> garnishList = new ArrayList<Garnish>();
        garnishList.add(garnish1);
        garnishList.add(garnish2);
        when(garnishService.getAllGarnish()).thenReturn(garnishList);
        mockMvc.perform(get("/garnish/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":0,\"type\":\"Tester\",\"storage\":\"Testing\"},{\"id\":0,\"type\":\"Tester\",\"storage\":\"Testing\"}]")));
    }

    @Test
    void testGetGarnish() throws Exception {
        Garnish garnish = createGarnishHelper("Tester", "Testing");
        when(garnishService.getGarnish(garnish.getId())).thenReturn(garnish);
        mockMvc.perform(get("/garnish/getGarnish?id=" + garnish.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":0,\"type\":\"Tester\",\"storage\":\"Testing\"}")));
    }

    @Test
    void testAddGlass() throws Exception {
        mockMvc.perform(post("/garnish/addGarnish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"Pint\",\"volume\":568}"))
                .andExpect(status().isOk());
        verify(garnishService).addGarnish(any(Garnish.class));
    }

    @Test
    void testUpdateGarnish() throws Exception {
        mockMvc.perform(put("/garnish/updateGarnish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createGarnishHelper("Tester", "Testing").toString()))
                .andExpect(status().isOk());
        verify(garnishService).updateGarnish(any(Garnish.class));
    }

    @Test
    void testDeleteGarnish() throws Exception {
        when(garnishService.deleteGarnish(2)).thenReturn("Garnish Deleted");
        mockMvc.perform(delete("/garnish/deleteGarnish?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Garnish Deleted")));
        verify(garnishService).deleteGarnish(2);
    }


}
