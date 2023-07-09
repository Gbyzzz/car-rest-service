package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.foxminded.pinchuk.javaspring.carrestservice.IntegrationTestBase;
import ua.foxminded.pinchuk.javaspring.carrestservice.Source;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.BrandMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;

import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BrandControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BrandMapper mapper;

    @Test
    void getAllBrands() throws Exception {
        String expectedJson = new ObjectMapper().writeValueAsString(Source.brands.stream().map(mapper).collect(Collectors.toList()));
        MvcResult result = mvc.perform(get("/api/v1/manufacturer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(Source.brands.size())))
                .andReturn();

        String actualJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, actualJson);
    }


    @ParameterizedTest
    @MethodSource("ua.foxminded.pinchuk.javaspring.carrestservice.Source#provideBrandById")
    void getBrandById(Brand brand, int id) throws Exception {
        MvcResult result = mvc.perform(get("/api/v1/manufacturer/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(new ObjectMapper().writeValueAsString(mapper.apply(brand)),
                result.getResponse().getContentAsString());
    }

    @Test
    @Order(1)
    @WithMockUser(authorities = "add:brand")
    void addBrand() throws Exception {
        String toAdd = new ObjectMapper().writeValueAsString(Source.brand4);
        mvc.perform(post("/api/v1/manufacturer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toAdd))
                .andExpect(status().isOk());
        Source.brands.add(Source.brand4);
        getAllBrands();
    }

    @Test
    @Order(2)
    @WithMockUser(authorities = "update:brand")
    void updateBrand() throws Exception {
        Source.brands.get(3).setName("Jeep");
        String toUpdate = new ObjectMapper().writeValueAsString(Source.brand4);
        mvc.perform(put("/api/v1/manufacturer").
                contentType(MediaType.APPLICATION_JSON)
                .content(toUpdate))
                .andExpect(status().isOk());
        getAllBrands();
    }

    @Test
    @Order(3)
    @WithMockUser(authorities = "delete:brand")
    void deleteBrand() throws Exception {
        String toDelete = new ObjectMapper().writeValueAsString(Source.brand4);
        mvc.perform(delete("/api/v1/manufacturer").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(toDelete))
                .andExpect(status().isOk());
        Source.brands.remove(3);
        getAllBrands();
    }
}