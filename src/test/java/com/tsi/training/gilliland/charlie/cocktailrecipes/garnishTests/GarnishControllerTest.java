package com.tsi.training.gilliland.charlie.cocktailrecipes.garnishTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.Garnish;
import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.GarnishController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.garnish.GarnishService;
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
@WebMvcTest(GarnishController.class)
public class GarnishControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GarnishService garnishService;

    private static Garnish createGarnishHelper(String type, String storage) {
        Garnish garnish = new Garnish();
        garnish.setType(type);
        garnish.setStorage(storage);
        return garnish;
    }

    @Test
    public void testGetAllEmpty() throws Exception {
        when(garnishService.getAllGarnish()).thenReturn(new ArrayList<Garnish>());
        mockMvc.perform(get("/garnish/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void testGetAllWithGarnish() throws Exception {
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
    public void testGetGarnish() throws Exception {
        Garnish garnish = createGarnishHelper("Tester", "Testing");
        when(garnishService.getGarnish(garnish.getId())).thenReturn(garnish);
        mockMvc.perform(get("/garnish/getGarnish?id=" + garnish.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":0,\"type\":\"Tester\",\"storage\":\"Testing\"}")));
    }

    @Test
    public void testAddGlass() throws Exception {
        mockMvc.perform(post("/garnish/addGarnish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"Pint\",\"volume\":568}"))
                .andExpect(status().isOk());
        verify(garnishService).addGarnish(any(Garnish.class));
    }

    @Test
    public void testUpdateGarnish() throws Exception {
        mockMvc.perform(put("/garnish/updateGarnish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createGarnishHelper("Tester", "Testing").toString()))
                .andExpect(status().isOk());
        verify(garnishService).updateGarnish(any(Garnish.class));
    }

    @Test
    public void testDeleteGarnish() throws Exception {
        when(garnishService.deleteGarnish(2)).thenReturn("Garnish Deleted");
        mockMvc.perform(delete("/garnish/deleteGarnish?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Garnish Deleted")));
        verify(garnishService).deleteGarnish(2);
    }


}
