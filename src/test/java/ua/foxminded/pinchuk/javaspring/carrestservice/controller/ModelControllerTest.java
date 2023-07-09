package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.foxminded.pinchuk.javaspring.carrestservice.IntegrationTestBase;
import ua.foxminded.pinchuk.javaspring.carrestservice.Source;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.ModelMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.ModelService;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(OrderAnnotation.class)
class ModelControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ModelService modelService;

    @ParameterizedTest
    @MethodSource("ua.foxminded.pinchuk.javaspring.carrestservice.Source#provideModelsByBrand")
    void getAllModelsOfBrand(String url, List<Model> models) throws Exception {

        String expectedJson = new ObjectMapper().writeValueAsString(models.stream().map(mapper).collect(Collectors.toList()));
        MvcResult result = mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, actualJson);
    }

    @ParameterizedTest
    @MethodSource("ua.foxminded.pinchuk.javaspring.carrestservice.Source#provideModelsByBrandAndName")
    void getAllByBrandAndModelName(String url, List<Model> models) throws Exception {
        String expectedJson = new ObjectMapper().writeValueAsString(models.stream().map(mapper).collect(Collectors.toList()));
        MvcResult result = mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, actualJson);
    }

    @ParameterizedTest
    @MethodSource("ua.foxminded.pinchuk.javaspring.carrestservice.Source#provideModelByBrandAndNameAndYear")
    void getAllByBrandAndModelNameAndYear(String url, Model model) throws Exception {
        String expectedJson = new ObjectMapper().writeValueAsString(mapper.apply(model));
        MvcResult result = mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, actualJson);
    }

    @Test
    @Order(1)
    @WithMockUser(authorities = "add:model")
    void addModel() throws Exception {
        String typesOfCar = new ObjectMapper().writeValueAsString(Source.model10.getTypes().stream()
                .map(Type::getName).collect(Collectors.toList()));
        mvc.perform(post("/api/v1/manufacturers/{brand}/{name}/{year}",
                        Source.model10.getBrand().getName(), Source.model10.getName(),
                        Source.model10.getYear()).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(typesOfCar))
                .andExpect(status().isOk());
        Source.models.add(Source.model10);
        assertEquals(Source.models.stream().map(mapper).collect(Collectors.toList()),
                modelService.findAll());
    }

    @Test
    @Order(2)
    @WithMockUser(authorities = "update:model")
    void updateModel() throws Exception {
        Source.models.get(9).getTypes().add(Source.type3);
        String typesOfCar = new ObjectMapper().writeValueAsString(Source.model10.getTypes().stream()
                .map(Type::getName).collect(Collectors.toList()));
        mvc.perform(put("/api/v1/manufacturers/{brand}/{name}/{year}",
                        Source.model10.getBrand().getName(), Source.model10.getName(),
                        Source.model10.getYear()).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(typesOfCar))
                .andExpect(status().isOk());
        assertEquals(Source.models.stream().map(mapper).collect(Collectors.toList()),
                modelService.findAll());
    }

    @Test
    @Order(3)
    @WithMockUser(authorities = "delete:model")
    void deleteModel() throws Exception {
        mvc.perform(delete("/api/v1/manufacturers/{brand}/{name}/{year}",
                        Source.model10.getBrand().getName(), Source.model10.getName(),
                        Source.model10.getYear()).
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Source.models.remove(9);
        assertEquals(Source.models.stream().map(mapper).collect(Collectors.toList()),
                modelService.findAll());
    }
}