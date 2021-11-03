package com.example.testavimas1.controller;

import com.example.testavimas1.service.VartotojasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = VartotojasRESTController.class)
public class VartotojasRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    VartotojasService serviceMock;
}
