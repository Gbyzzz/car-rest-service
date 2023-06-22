package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    List<ModelDTO> getAllModelsOfBrand(@PathVariable String brand,
                                       @RequestParam(name = "year_min", required = false) Integer yearMin,
                                       @RequestParam(name = "year_max", required = false) Integer yearMax,
                                       @RequestParam(name = "type", required = false) String type,
                                       @RequestParam(name = "page", required = false) Integer page,
                                       @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return modelService.searchModel(brand, null, yearMin, yearMax, type, page, pageSize);
    }

    @GetMapping("/{brand}/{name}")
    List<ModelDTO> getAllByBrandAndModelName(@PathVariable String brand, @PathVariable String name,
                                          @RequestParam(name = "year_min", required = false) Integer yearMin,
                                          @RequestParam(name = "year_max", required = false) Integer yearMax,
                                          @RequestParam(name = "type", required = false) String type,
                                          @RequestParam(name = "page", required = false) Integer page,
                                          @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return modelService.searchModel(brand, name, yearMin, yearMax, type, page, pageSize);
    }

    @GetMapping("/{brand}/{name}/{year}")
    ModelDTO getAllByBrandAndModelNameAndYear(@PathVariable String brand, @PathVariable String name,
                                           @PathVariable Integer year) {
        return modelService.findAllByBrandAndNameAndYear(brand, name, year);
    }

    @PostMapping("/{brand}/{name}/{year}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void addModel(@PathVariable String brand, @PathVariable String name, @PathVariable Integer year, @RequestBody List<String> typeNames) {
        modelService.add(brand, name, year, typeNames);
    }

    @PutMapping("/{brand}/{name}/{year}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void saveModel(@PathVariable String brand, @PathVariable String name, @PathVariable Integer year, @RequestBody List<String> typeNames) {
        modelService.add(brand, name, year, typeNames);
    }

    @DeleteMapping("/{brand}/{name}/{year}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void deleteModel(@PathVariable String brand, @PathVariable String name, @PathVariable Integer year) {
        modelService.remove(brand, name, year);
    }
}
