package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/{brand}")
    @RouterOperation(operation = @Operation(summary = "getAllModelsOfBrand",
            responses = @ApiResponse(responseCode = "200", content = @Content(
                    array = @ArraySchema(arraySchema = @Schema(implementation = ModelDTO.class))))))
    List<ModelDTO> getAllModelsOfBrand(@PathVariable String brand,
                                       @RequestParam(name = "year_min", required = false) Integer yearMin,
                                       @RequestParam(name = "year_max", required = false) Integer yearMax,
                                       @RequestParam(name = "type", required = false) String type,
                                       @RequestParam(name = "page", required = false) Integer page,
                                       @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return modelService.searchModel(brand, null, yearMin, yearMax, type, page, pageSize);
    }

    @GetMapping("/{brand}/{name}")
    @RouterOperation(operation = @Operation(summary = "getAllByBrandAndModelName",
            responses = @ApiResponse(responseCode = "200", content = @Content(
                    array = @ArraySchema(arraySchema = @Schema(implementation = ModelDTO.class))))))
    List<ModelDTO> getAllByBrandAndModelName(@PathVariable String brand, @PathVariable String name,
                                          @RequestParam(name = "year_min", required = false) Integer yearMin,
                                          @RequestParam(name = "year_max", required = false) Integer yearMax,
                                          @RequestParam(name = "type", required = false) String type,
                                          @RequestParam(name = "page", required = false) Integer page,
                                          @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return modelService.searchModel(brand, name, yearMin, yearMax, type, page, pageSize);
    }

    @GetMapping("/{brand}/{name}/{year}")
    @RouterOperation(operation = @Operation(summary = "getAllByBrandAndModelNameAndYear",
            responses = @ApiResponse(responseCode = "200", content = @Content(
                    schema = @Schema(implementation = ModelDTO.class)))))
    ModelDTO getAllByBrandAndModelNameAndYear(@PathVariable String brand, @PathVariable String name,
                                           @PathVariable Integer year) {
        return modelService.findAllByBrandAndNameAndYear(brand, name, year);
    }

    @PostMapping("/{brand}/{name}/{year}")
    @RouterOperation(operation = @Operation(summary = "addModel",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void addModel(@PathVariable String brand, @PathVariable String name,
                  @PathVariable Integer year, @RequestBody List<String> typeNames) {
        modelService.add(brand, name, year, typeNames);
    }

    @PutMapping("/{brand}/{name}/{year}")
    @RouterOperation(operation = @Operation(summary = "saveModel",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void saveModel(@PathVariable String brand, @PathVariable String name,
                   @PathVariable Integer year, @RequestBody List<String> typeNames) {
        modelService.add(brand, name, year, typeNames);
    }

    @DeleteMapping("/{brand}/{name}/{year}")
    @RouterOperation(operation = @Operation(summary = "deleteModel",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void deleteModel(@PathVariable String brand, @PathVariable String name,
                     @PathVariable Integer year) {
        modelService.remove(brand, name, year);
    }
}
