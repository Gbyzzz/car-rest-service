package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import org.springframework.web.bind.annotation.*;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manufacturer")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    List<Brand> getAllBrands() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    Brand getBrandById(@PathVariable Integer id) throws Exception {
        return brandService.findById(id);
    }

    @PostMapping("")
    void addBrand(@RequestBody Brand brand) {
        brandService.saveOrUpdate(brand);
    }

    @PutMapping("")
    void saveBrand(@RequestBody Brand brand) {
        brandService.saveOrUpdate(brand);
    }

    @DeleteMapping("")
    void deleteBrand(@RequestBody Brand brand) {
        brandService.remove(brand);
    }
}
