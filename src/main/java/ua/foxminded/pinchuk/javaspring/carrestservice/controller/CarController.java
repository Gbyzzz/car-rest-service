package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
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
    List<Car> getCars(@RequestParam(name = "manufacturer", required = false) String brandName,
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
    Car getCarById(@PathVariable Integer id) throws Exception {
        return carService.findById(id);
    }

    @PostMapping("")
    void addCar(@RequestBody Car car) {
        carService.saveOrUpdate(car);
    }

    @PutMapping("")
    void saveCar(@RequestBody Car car) {
        carService.saveOrUpdate(car);
    }

    @DeleteMapping("")
    void deleteCar(@RequestBody Car car) {
        carService.remove(car);
    }
}
