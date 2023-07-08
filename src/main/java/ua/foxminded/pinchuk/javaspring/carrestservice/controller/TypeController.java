package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.TypeDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.TypeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/types")
@Tag(name = "Type API")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("")
    @Operation(summary = "Get all types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            array = @ArraySchema(
                                    arraySchema = @Schema(implementation = ModelDTO.class))))})
    List<TypeDTO> getAllTypes() {
        return typeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get type by id", description = "Type must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = TypeDTO.class)))})
    TypeDTO getTypeById(@PathVariable Long id) throws Exception {
        return typeService.findById(id);
    }

    @PostMapping("")
    @Operation(summary = "Add type")
    @SecurityRequirement(name = "bearerAuth", scopes = {"add:type"})
    @PreAuthorize("hasAnyAuthority('add:type')")
    void addType(@RequestBody Type type) throws Exception {
        typeService.saveOrUpdate(type);
    }

    @PutMapping("")
    @Operation(summary = "Update type")
    @SecurityRequirement(name = "bearerAuth", scopes = {"update:type"})
    @PreAuthorize("hasAnyAuthority('update:type')")
    void updateType(@RequestBody Type type) throws Exception {
        typeService.saveOrUpdate(type);
    }

    @DeleteMapping("")
    @Operation(summary = "Delete type")
    @SecurityRequirement(name = "bearerAuth", scopes = {"delete:type"})
    @PreAuthorize("hasAnyAuthority('delete:type')")
    void deleteType(@RequestBody Type type) throws Exception {
        typeService.remove(type);
    }
}
