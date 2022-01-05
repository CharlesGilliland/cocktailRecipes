package com.tsi.training.gilliland.charlie.cocktailrecipes.glassTests;

import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.Glass;
import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.GlassController;
import com.tsi.training.gilliland.charlie.cocktailrecipes.glass.GlassService;
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
@WebMvcTest(GlassController.class)
public class GlassControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    GlassService glassService;

    private static Glass createGlassHelper(String type, int volume) {
        Glass glass = new Glass();
        glass.setType(type);
        glass.setVolume(volume);
        return glass;
    }

    @Test
    public void testGetAllEmpty() throws Exception {
        when(glassService.getGlasses()).thenReturn(new ArrayList<Glass>());
        mockMvc.perform(get("/glass/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void testGetAllWithGlasses() throws Exception {
        Glass glass1 = createGlassHelper("Pint", 568);
        Glass glass2 = createGlassHelper("Shot", 35);
        List<Glass> glassList = new ArrayList<Glass>();
        glassList.add(glass1);
        glassList.add(glass2);
        when(glassService.getGlasses()).thenReturn(glassList);
        mockMvc.perform(get("/glass/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":0,\"type\":\"Pint\",\"volume\":568},{\"id\":0,\"type\":\"Shot\",\"volume\":35}]")));
    }

    @Test
    public void testGetGlass() throws Exception {
        Glass glass = createGlassHelper("Pint", 568);
        when(glassService.getGlass(glass.getId())).thenReturn(glass);
        mockMvc.perform(get("/glass/getGlass?id=" + glass.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":0,\"type\":\"Pint\",\"volume\":568}")));
    }

    @Test
    public void testAddGlass() throws Exception {
        mockMvc.perform(post("/glass/addGlass")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"type\":\"Pint\",\"volume\":568}"))
                .andExpect(status().isOk());
        verify(glassService).addGlass(any(Glass.class));
    }

    @Test
    public void testUpdateGlass() throws Exception {
        mockMvc.perform(put("/glass/updateGlass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createGlassHelper("Test", 568).toString()))
                .andExpect(status().isOk());
        verify(glassService).updateGlass(any(Glass.class));
    }

    @Test
    public void testDeleteGlass() throws Exception {
        when(glassService.deleteGlass(2)).thenReturn("Glass Deleted");
        mockMvc.perform(delete("/glass/deleteGlass?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Glass Deleted")));
        verify(glassService).deleteGlass(2);
    }
}
