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
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.CarDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;
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
    @Operation(summary = "Get all models by brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            array = @ArraySchema(
                                    arraySchema = @Schema(implementation = ModelDTO.class))))})
    List<ModelDTO> getAllModelsByBrand(@PathVariable String brand,
                                       @RequestParam(name = "year_min", required = false) Integer yearMin,
                                       @RequestParam(name = "year_max", required = false) Integer yearMax,
                                       @RequestParam(name = "type", required = false) String type,
                                       @RequestParam(name = "page", required = false) Integer page,
                                       @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return modelService.searchModel(brand, null, yearMin, yearMax, type, page, pageSize);
    }

    @GetMapping("/{brand}/{name}")
    @Operation(summary = "Get models by brand and name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            array = @ArraySchema
                                    (arraySchema = @Schema(implementation = ModelDTO.class))))})
    List<ModelDTO> getAllByBrandAndModelName(@PathVariable String brand, @PathVariable String name,
                                             @RequestParam(name = "year_min", required = false) Integer yearMin,
                                             @RequestParam(name = "year_max", required = false) Integer yearMax,
                                             @RequestParam(name = "type", required = false) String type,
                                             @RequestParam(name = "page", required = false) Integer page,
                                             @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return modelService.searchModel(brand, name, yearMin, yearMax, type, page, pageSize);
    }

    @GetMapping("/{brand}/{name}/{year}")
    @Operation(summary = "Get models by brand, name and year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = ModelDTO.class)))})
    ModelDTO getAllByBrandAndModelNameAndYear(@PathVariable String brand, @PathVariable String name,
                                              @PathVariable Integer year) {
        return modelService.findAllByBrandAndNameAndYear(brand, name, year);
    }

    @PostMapping("/{brand}/{name}/{year}")
    @Operation(summary = "Add model")
    @SecurityRequirement(name = "bearerAuth", scopes = {"add:model"})
    @PreAuthorize("hasAnyAuthority('add:model')")
    void addModel(@PathVariable String brand, @PathVariable String name,
                  @PathVariable Integer year, @RequestBody List<String> typeNames) {
        modelService.add(brand, name, year, typeNames);
    }

    @PutMapping("/{brand}/{name}/{year}")
    @Operation(summary = "Update model")
    @SecurityRequirement(name = "bearerAuth", scopes = {"update:model"})
    @PreAuthorize("hasAnyAuthority('update:model')")
    void updateModel(@PathVariable String brand, @PathVariable String name,
                   @PathVariable Integer year, @RequestBody List<String> typeNames) {
        modelService.add(brand, name, year, typeNames);
    }

    @DeleteMapping("/{brand}/{name}/{year}")
    @Operation(summary = "Delete model")
    @SecurityRequirement(name = "bearerAuth", scopes = {"delete:model"})
    @PreAuthorize("hasAnyAuthority('delete:model')")
    void deleteModel(@PathVariable String brand, @PathVariable String name,
                     @PathVariable Integer year) {
        modelService.remove(brand, name, year);
    }
}
