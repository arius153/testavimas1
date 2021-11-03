package com.example.testavimas1.controller;

import com.example.testavimas1.model.Vartotojas;
import com.example.testavimas1.model.Veiksmas;
import com.example.testavimas1.model.VeiksmasDTO;
import com.example.testavimas1.service.VartotojasService;
import com.example.testavimas1.service.VeiksmasService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(value = VeiksmaiController.class)
public class VeiksmasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private VeiksmasService veiksmasService;

    @MockBean
    private VartotojasService vartotojasService;

    @Test
    public void givenWac_whenServletContext_thenItProvidesController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("veiksmaiController")); // pirma kontrolerio pav-mo raide mazoji
    }

    @Test
    public void testGetVeiksmai() throws Exception {
        Vartotojas v1 = new Vartotojas(1L, "Arijus", "8611111234", null);
        List<Veiksmas> veiksmai = new ArrayList<>();
        veiksmai.add(new Veiksmas(1L, "insert", LocalDate.now(), v1));
        veiksmai.add(new Veiksmas(2L, "delete", LocalDate.now(), v1));
        when(veiksmasService.findAll()).thenReturn(veiksmai);

        RequestBuilder rb = MockMvcRequestBuilders.get("/veiksmai").accept(MediaType.TEXT_HTML);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("veiksmai"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/veiksmai.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("veiksmai"))
                .andReturn();
    }

    @Test
    public void testShowAddPage() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-veiksmas");

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-veiksmas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/add-veiksmas.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("veiksmas"))
                .andExpect(MockMvcResultMatchers.model().attribute("veiksmas", hasProperty("veiksmas", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("veiksmas", hasProperty("data", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("veiksmas", hasProperty("vartotojoId", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attributeExists("vartotojai"))
                .andReturn();
    }

    @Test
    public void testAdd() throws Exception {
        LocalDate dateNow = LocalDate.now();
        Vartotojas v1 = new Vartotojas(1L, "Arijus", "8611111234", null);
        Veiksmas veiksmas = new Veiksmas(1L, "insert", dateNow, v1);
        when(veiksmasService.add(Mockito.any(Veiksmas.class))).thenReturn(veiksmas);

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-veiksmas")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("veiksmas", "Arijus")
                .param("data", "2021-11-09")
                .param("vartotojoId", "1")
                .flashAttr("veiksmas", new VeiksmasDTO("Arijus", LocalDate.now(), 1L));

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/veiksmai"))
                .andReturn();

        verify(veiksmasService).add(Mockito.any(Veiksmas.class));
    }

    @Test
    public void testShowUpdatePage() throws Exception {

        LocalDate dateNow = LocalDate.now();
        Vartotojas v1 = new Vartotojas(1L, "Arijus", "8611111234", null);
        Veiksmas veiksmas = new Veiksmas(1L, "insert", dateNow, v1);
        VeiksmasDTO veiksmasDTO = new VeiksmasDTO(1L, "insert", dateNow, 1L);
        when(veiksmasService.findById(Mockito.anyLong())).thenReturn(veiksmas);
        when(veiksmasService.mapVeiksmasToDTO(Mockito.any(Veiksmas.class))).thenReturn(veiksmasDTO);
        RequestBuilder rb = MockMvcRequestBuilders.get("/update-veiksmas/1");

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-veiksmas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/add-veiksmas.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("veiksmas"))
                .andExpect(MockMvcResultMatchers.model().attribute("veiksmas", hasProperty("veiksmas", Matchers.equalTo("insert"))))
                .andExpect(MockMvcResultMatchers.model().attribute("veiksmas", hasProperty("data", Matchers.equalTo(dateNow))))
                .andExpect(MockMvcResultMatchers.model().attribute("veiksmas", hasProperty("vartotojoId", Matchers.equalTo(1L))))
                .andExpect(MockMvcResultMatchers.model().attributeExists("vartotojai"))
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/delete-veiksmas/1");

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/veiksmai"))
                .andReturn();
        verify(veiksmasService).deleteById(Mockito.anyLong());
    }

}
