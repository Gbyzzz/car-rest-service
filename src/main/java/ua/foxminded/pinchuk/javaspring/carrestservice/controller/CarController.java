package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    @RouterOperation(operation = @Operation(summary = "getCars", security =
    @SecurityRequirement(name = "bearerAuth", scopes = {"ADMIN", "STAFF"}),
            responses = @ApiResponse(responseCode = "200", content = @Content(
                    array = @ArraySchema(
                            arraySchema = @Schema(implementation = CarDTO.class))))))
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
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
    @RouterOperation(operation = @Operation(summary = "getCarById",
            responses = @ApiResponse(responseCode = "200", content = @Content(
                    schema = @Schema(implementation = CarDTO.class)))))
    CarDTO getCarById(@PathVariable Long id) throws Exception {
        return carService.findById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @RouterOperation(operation = @Operation(summary = "addCar",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    void addCar(@RequestBody Car car) {
        carService.saveOrUpdate(car);
    }

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @RouterOperation(operation = @Operation(summary = "saveCar",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    void saveCar(@RequestBody Car car) {
        carService.saveOrUpdate(car);
    }

    @DeleteMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @RouterOperation(operation = @Operation(summary = "deleteCar",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    void deleteCar(@RequestBody Car car) {
        carService.remove(car);
    }
}
