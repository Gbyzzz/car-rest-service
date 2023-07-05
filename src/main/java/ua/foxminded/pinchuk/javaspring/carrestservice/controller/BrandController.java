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
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.BrandDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
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
    @RouterOperation(operation = @Operation(summary = "getAllBrands", security = {},
            responses = @ApiResponse(responseCode = "200", content = @Content(
                    array = @ArraySchema(arraySchema = @Schema(implementation = BrandDTO.class))))))
    List<BrandDTO> getAllBrands() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    @RouterOperation(operation = @Operation(summary = "getAllBrands", security = {},
            responses = @ApiResponse(responseCode = "200", content = @Content(
                    schema = @Schema(implementation = BrandDTO.class)))))
    BrandDTO getBrandById(@PathVariable Long id) throws Exception {
        return brandService.findById(id);
    }

    @PostMapping("")
    @RouterOperation(operation = @Operation(summary = "addBrand",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void addBrand(@RequestBody Brand brand) {
        brandService.saveOrUpdate(brand);
    }

    @PutMapping("")
    @RouterOperation(operation = @Operation(summary = "saveBrand",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void saveBrand(@RequestBody Brand brand) {
        brandService.saveOrUpdate(brand);
    }

    @DeleteMapping("")
    @RouterOperation(operation = @Operation(summary = "deleteBrand",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void deleteBrand(@RequestBody Brand brand) {
        brandService.remove(brand);
    }
}
