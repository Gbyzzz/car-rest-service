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
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.CarDTO;
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

    @GetMapping
    @Operation(summary = "Get all cars", description = "Get all cars from db")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = CarDTO.class))))
    @ApiResponse(responseCode = "404", description = "Unauthorized or lack of required authority",
            content = {@Content()})
    @SecurityRequirement(name = "bearerAuth", scopes = {"read:car"})
    @PreAuthorize("hasAuthority('read:car')")
    List<CarDTO> getCars(
            @RequestParam(name = "brand", required = false) String brandName,
                         @RequestParam(name = "year_min", required = false) Integer yearMin,
                         @RequestParam(name = "year_max", required = false) Integer yearMax,
                         @RequestParam(name = "type", required = false) String type,
                         @RequestParam(name = "color", required = false) String color,
                         @RequestParam(name = "model_name", required = false) String modelName,
                         @RequestParam(name = "page", required = false) Integer page,
                         @RequestParam(name = "page_size", required = false) Integer pageSize
            ) {
        return carService.searchCar(brandName, yearMin, yearMax, type, color, modelName,
                page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get car by id", description = "Get car by id from db. Car must exist")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CarDTO.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error. Not valid id",
            content = @Content())
    CarDTO getCarById(@PathVariable Long id) throws Exception {
        return carService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('add:car')")
    @Operation(summary = "Add car", description = "Add car to db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"add:car"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Unauthorized or lack of required authority",
            content = {@Content()})
    void addCar(@RequestBody Car car) {
        carService.saveOrUpdate(car);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('update:car')")
    @Operation(summary = "Update car", description = "Update existing car in db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"update:car"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Unauthorized or lack of required authority",
            content = {@Content()})
    void updateCar(@RequestBody Car car) {
        carService.saveOrUpdate(car);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('delete:car')")
    @Operation(summary = "Delete car", description = "Delete existing car from db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"delete:car"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Unauthorized or lack of required authority",
            content = {@Content()})
    void deleteCar(@RequestBody Car car) {
        carService.remove(car);
    }
}
