package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.BrandDTO;
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
    List<BrandDTO> getAllBrands() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    BrandDTO getBrandById(@PathVariable Long id) throws Exception {
        return brandService.findById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void addBrand(@RequestBody Brand brand) {
        brandService.saveOrUpdate(brand);
    }

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void saveBrand(@RequestBody Brand brand) {
        brandService.saveOrUpdate(brand);
    }

    @DeleteMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void deleteBrand(@RequestBody Brand brand) {
        brandService.remove(brand);
    }
}
