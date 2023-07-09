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
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.CarMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Car;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.CarService;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarMapper mapper;

    @Autowired
    private CarService carService;

    @ParameterizedTest
    @WithMockUser(authorities = "read:car")
    @MethodSource("ua.foxminded.pinchuk.javaspring.carrestservice.Source#provideCars")
    void getCars(String url, List<Car> cars) throws Exception {
        String expectedJson = new ObjectMapper().writeValueAsString(cars.stream()
                .map(mapper).collect(Collectors.toList()));
        MvcResult result = mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, actualJson);
    }

    @ParameterizedTest
    @MethodSource("ua.foxminded.pinchuk.javaspring.carrestservice.Source#provideCarById")
    void getCarById(Car car, int id) throws Exception {
        MvcResult result = mvc.perform(get("/api/v1/cars/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(new ObjectMapper().writeValueAsString(mapper.apply(car)),
                result.getResponse().getContentAsString());
    }

    @Test
    @Order(1)
    @WithMockUser(authorities = "add:car")
    void addCar() throws Exception {
        String toAdd = new ObjectMapper().writeValueAsString(Source.car5);
        mvc.perform(post("/api/v1/cars").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(toAdd))
                .andExpect(status().isOk());
        Source.cars.add(Source.car5);
        assertEquals(Source.cars,carService.findAll());
    }

    @Test
    @Order(2)
    @WithMockUser(authorities = "update:car")
    void updateCar() throws Exception {
        Source.cars.get(4).setCarColor("Purple");
        String toUpdate = new ObjectMapper().writeValueAsString(Source.car5);
        mvc.perform(put("/api/v1/cars").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(toUpdate))
                .andExpect(status().isOk());
        assertEquals(Source.cars,carService.findAll());
    }

    @Test
    @Order(3)
    @WithMockUser(authorities = "delete:car")
    void deleteCar() throws Exception {
        String toDelete = new ObjectMapper().writeValueAsString(Source.car5);
        mvc.perform(delete("/api/v1/cars").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(toDelete))
                .andExpect(status().isOk());
        Source.cars.remove(4);
        assertEquals(Source.cars,carService.findAll());
    }
}