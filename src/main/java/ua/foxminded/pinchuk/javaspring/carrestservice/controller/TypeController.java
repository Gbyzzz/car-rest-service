package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.TypeDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.TypeService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemAlreadyExists;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/type")
@Tag(name = "Type API")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    @Operation(summary = "Get all types", description = "Get all types from db")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = TypeDTO.class))))
    Set<TypeDTO> getAllTypes() {
        return typeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get type by id",
            description = "Get type by id from db. Type must exist")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TypeDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request. Not valid id",
            content = @Content())
    TypeDTO getTypeById(@PathVariable Long id) throws Exception {
        return typeService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('add:type')")
    @Operation(summary = "Add type", description = "Add type to db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"add:type"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400",
            description = "Bad Request. Type with this name already exists",
            content = @Content())
    @ApiResponse(responseCode = "404", description = "Unauthorized or lack of required authority",
            content = {@Content()})
    void addType(@RequestBody String name) throws ItemAlreadyExists {
        typeService.add(name);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('update:type')")
    @Operation(summary = "Update type", description = "Update existing type in db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"update:type"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Unauthorized or lack of required authority",
            content = {@Content()})
    void updateType(@RequestBody TypeDTO typeDTO) {
        typeService.update(typeDTO);
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasAuthority('delete:type')")
    @Operation(summary = "Delete type", description = "Delete existing type from db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"delete:type"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Unauthorized or lack of required authority",
            content = {@Content()})
    void deleteType(@PathVariable String name) {
        typeService.removeByName(name);
    }
}
