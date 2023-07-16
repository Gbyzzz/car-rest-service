package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.foxminded.pinchuk.javaspring.carrestservice.IntegrationTestBase;
import ua.foxminded.pinchuk.javaspring.carrestservice.Source;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.TypeMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;

import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TypeControllerTest  extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TypeMapper mapper;

    @Test
    void getAllTypes() throws Exception {

        String expectedJson = new ObjectMapper().writeValueAsString(Source.types.stream()
                .map(mapper).collect(Collectors.toSet()));
        MvcResult result = mvc.perform(get("/api/v1/type")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(Source.types.size())))
                .andReturn();

        String actualJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, actualJson);
    }


    @ParameterizedTest
    @MethodSource("ua.foxminded.pinchuk.javaspring.carrestservice.Source#provideTypesById")
    void getTypeById(Type type, int id) throws Exception {
        MvcResult result = mvc.perform(get("/api/v1/type/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(new ObjectMapper().writeValueAsString(mapper.apply(type)),
                result.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(authorities = "add:type")
    void addType() throws Exception {
        mvc.perform(post("/api/v1/type").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(Source.type4.getName()))
                .andExpect(status().isOk());
        Source.types.add(Source.type4);
        getAllTypes();
    }

    @Test
    @WithMockUser(authorities = "update:type")
    void updateType() throws Exception {
        Source.types.get(3).setName("Sport Utility Vehicle");
        String toUpdate = new ObjectMapper().writeValueAsString(Source.types.get(3));
        mvc.perform(put("/api/v1/type").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(toUpdate))
                .andExpect(status().isOk());
        getAllTypes();
    }

    @Test
    @WithMockUser(authorities = "delete:type")
    void deleteType() throws Exception {
        String toDelete = new ObjectMapper().writeValueAsString(Source.types.get(3));
        mvc.perform(delete("/api/v1/type/" + Source.types.get(3).getName()).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(toDelete))
                .andExpect(status().isOk());
        Source.types.remove(3);
        getAllTypes();
    }
}