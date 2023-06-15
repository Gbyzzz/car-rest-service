package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.foxminded.pinchuk.javaspring.carrestservice.IntegrationTestBase;
import ua.foxminded.pinchuk.javaspring.carrestservice.Source;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.BrandService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.ModelService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ModelControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;


    @ParameterizedTest
    @MethodSource("ua.foxminded.pinchuk.javaspring.carrestservice.Source#provideModels")
    void getAllModelsOfBrand(String url, List<Model> models) throws Exception {

        String expectedJson = new ObjectMapper().writeValueAsString(models);
        MvcResult result = mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, actualJson);
    }

    @Test
    void addModel() {
    }

    @Test
    void saveModel() {
    }

    @Test
    void deleteModel() {
    }
}