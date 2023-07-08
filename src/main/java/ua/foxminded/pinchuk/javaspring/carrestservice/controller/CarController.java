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
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.CarDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Car;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@Tag(name = "Car API")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    @Operation(summary = "Get all cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(
            array = @ArraySchema(
                    arraySchema = @Schema(implementation = CarDTO.class))))})
    @SecurityRequirement(name = "bearerAuth", scopes = {"read:car"})
    @PreAuthorize("hasAuthority('read:car')")
    List<CarDTO> getCars(@RequestParam(name = "manufacturer", required = false) String brandName,
                         @RequestParam(name = "year_min", required = false) Integer yearMin,
                         @RequestParam(name = "year_max", required = false) Integer yearMax,
                         @RequestParam(name = "type", required = false) String type,
                         @RequestParam(name = "color", required = false) String color,
                         @RequestParam(name = "model_name", required = false) String modelName,
                         @RequestParam(name = "page", required = false) Integer page,
                         @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return carService.searchCar(brandName, yearMin, yearMax, type, color, modelName, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get car by id", description = "Car must exist")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", content = @Content(
                    schema = @Schema(implementation = CarDTO.class)))
    })
    CarDTO getCarById(@PathVariable Long id) throws Exception {
        return carService.findById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('add:car')")
    @Operation(summary = "Add car")
    @SecurityRequirement(name = "bearerAuth", scopes = {"add:car"})
    void addCar(@RequestBody Car car) {
        carService.saveOrUpdate(car);
    }

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('update:car')")
    @Operation(summary = "Update car")
    @SecurityRequirement(name = "bearerAuth", scopes = {"update:car"})
    void updateCar(@RequestBody Car car) {
        carService.saveOrUpdate(car);
    }

    @DeleteMapping("")
    @PreAuthorize("hasAnyAuthority('delete:car')")
    @Operation(summary = "Delete car")
    @SecurityRequirement(name = "bearerAuth", scopes = {"delete:car"})
    void deleteCar(@RequestBody Car car) {
        carService.remove(car);
    }
}
