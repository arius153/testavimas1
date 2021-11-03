package com.example.testavimas1.controller;

import com.example.testavimas1.model.Vartotojas;
import com.example.testavimas1.service.VartotojasService;
import com.example.testavimas1.service.VeiksmasService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(value = VartotojasController.class)
public class VartotojasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private VartotojasService vartotojasService;

    @Test
    public void givenWac_whenServletContext_thenItProvidesController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("vartotojasController")); // pirma kontrolerio pav-mo raide mazoji
    }

    @Test
    public void testGetVartotojai() throws Exception {
        List<Vartotojas> vartotojai = new ArrayList<>();
        vartotojai.add(new Vartotojas(1L, "Arijus", "8611111234", null));
        vartotojai.add(new Vartotojas(2L, "Arijus", "8611111234", null));
        when(vartotojasService.findAll()).thenReturn(vartotojai);

        RequestBuilder rb = MockMvcRequestBuilders.get("/vartotojas").accept(MediaType.TEXT_HTML);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("vartotojas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/vartotojas.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("vartotojai"))
                .andReturn();
    }

    @Test
    public void testShowAddPage() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-vartotojas");

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-vartotojas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/add-vartotojas.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("vartotojas"))
                .andExpect(MockMvcResultMatchers.model().attribute("vartotojas", hasProperty("vardas", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("vartotojas", hasProperty("telNr", emptyOrNullString())))
                .andReturn();
    }

    @Test
    public void testAdd() throws Exception {
        Vartotojas v1 = new Vartotojas(1L, "Arijus", "8611111234", null);
        when(vartotojasService.add(Mockito.any(Vartotojas.class))).thenReturn(v1);

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-vartotojas")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("vardas", "Arijus")
                .param("telNr", "8611111234")
                .flashAttr("vartotojas", new Vartotojas("Arijus", "8611111234"));

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/vartotojas"))
                .andReturn();

        verify(vartotojasService).add(Mockito.any(Vartotojas.class));
    }

    @Test
    public void testShowUpdatePage() throws Exception {
        Vartotojas v1 = new Vartotojas(1L, "Arijus", "8611111234", null);
        when(vartotojasService.findById(Mockito.anyLong())).thenReturn(v1);

        RequestBuilder rb = MockMvcRequestBuilders.get("/update-vartotojas/1");

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-vartotojas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/add-vartotojas.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("vartotojas"))
                .andExpect(MockMvcResultMatchers.model().attribute("vartotojas", hasProperty("vardas", Matchers.equalTo("Arijus"))))
                .andExpect(MockMvcResultMatchers.model().attribute("vartotojas", hasProperty("telNr", Matchers.equalTo("8611111234"))))
                .andReturn();
    }

    @Test
    public void testUpdate() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/update-vartotojas/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("vardas", "Arijus")
                .param("telNr", "8611111234")
                .flashAttr("vartotojas", new Vartotojas("Arijus", "8611111234"));

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/vartotojas"))
                .andReturn();
        verify(vartotojasService).update(Mockito.any(Vartotojas.class));
    }

    @Test
    public void testDelete() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/delete-vartotojas/1");

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/vartotojas"))
                .andReturn();
        verify(vartotojasService).deleteById(Mockito.anyLong());
    }
}
