package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.foxminded.pinchuk.javaspring.carrestservice.IntegrationTestBase;
import ua.foxminded.pinchuk.javaspring.carrestservice.Source;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.BrandService;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrandControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;


    @Test
    void getAllBrands() throws Exception {
        String expectedJson = new ObjectMapper().writeValueAsString(Source.brands);
            MvcResult result = mvc.perform(get("/api/v1/manufacturer")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(3)))
                    .andReturn();

            String actualJson = result.getResponse().getContentAsString();
            assertEquals(expectedJson, actualJson);
    }
}