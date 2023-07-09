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
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.ModelService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manufacturers")
@Tag(name = "Model API")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/{brand}")
    @Operation(summary = "Get all models by brand",
            description = "Get all models by brand from db")
    @ApiResponse(responseCode = "200",
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(implementation = ModelDTO.class))))
    List<ModelDTO> getAllModelsByBrand(@PathVariable String brand,
                                       @RequestParam(name = "year_min", required = false) Integer yearMin,
                                       @RequestParam(name = "year_max", required = false) Integer yearMax,
                                       @RequestParam(name = "type", required = false) String type,
                                       @RequestParam(name = "page", required = false) Integer page,
                                       @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return modelService.searchModel(brand, null, yearMin, yearMax, type, page, pageSize);
    }

    @GetMapping("/{brand}/{name}")
    @Operation(summary = "Get models by brand and name",
            description = "Get models by brand and name from db")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema
                            (schema = @Schema(implementation = ModelDTO.class))))
    List<ModelDTO> getAllByBrandAndModelName(@PathVariable String brand, @PathVariable String name,
                                             @RequestParam(name = "year_min", required = false) Integer yearMin,
                                             @RequestParam(name = "year_max", required = false) Integer yearMax,
                                             @RequestParam(name = "type", required = false) String type,
                                             @RequestParam(name = "page", required = false) Integer page,
                                             @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return modelService.searchModel(brand, name, yearMin, yearMax, type, page, pageSize);
    }

    @GetMapping("/{brand}/{name}/{year}")
    @Operation(summary = "Get models by brand, name and year",
            description = "Get models by brand, name and year from db")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ModelDTO.class)))
    ModelDTO getAllByBrandAndModelNameAndYear(@PathVariable String brand, @PathVariable String name,
                                              @PathVariable Integer year) {
        return modelService.findAllByBrandAndNameAndYear(brand, name, year);
    }

    @PostMapping("/{brand}/{name}/{year}")
    @PreAuthorize("hasAuthority('add:model')")
    @Operation(summary = "Add model", description = "Add model to db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"add:model"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = {@Content()})
    void addModel(@PathVariable String brand, @PathVariable String name,
                  @PathVariable Integer year, @RequestBody List<String> typeNames) {
        modelService.add(brand, name, year, typeNames);
    }

    @PutMapping("/{brand}/{name}/{year}")
    @PreAuthorize("hasAuthority('update:model')")
    @Operation(summary = "Update model", description = "Update existing model in db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"update:model"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = {@Content()})
    void updateModel(@PathVariable String brand, @PathVariable String name,
                     @PathVariable Integer year, @RequestBody List<String> typeNames) {
        modelService.add(brand, name, year, typeNames);
    }

    @DeleteMapping("/{brand}/{name}/{year}")
    @PreAuthorize("hasAuthority('delete:model')")
    @Operation(summary = "Delete model", description = "Delete existing model from db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"delete:model"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = {@Content()})
    void deleteModel(@PathVariable String brand, @PathVariable String name,
                     @PathVariable Integer year) {
        modelService.remove(brand, name, year);
    }
}
