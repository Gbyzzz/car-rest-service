package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.BrandDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manufacturer")
@Tag(name = "Brand API")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    @Operation(summary = "Get all brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            array = @ArraySchema(
                                    arraySchema = @Schema(implementation = BrandDTO.class))))})
    List<BrandDTO> getAllBrands() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get brand by id", description = "Brand must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = BrandDTO.class)))})
    BrandDTO getBrandById(@PathVariable Long id) throws Exception {
        return brandService.findById(id);
    }

    @PostMapping("")
    @Operation(summary = "Add brand")
    @SecurityRequirement(name = "bearerAuth", scopes = {"add:brand"})
    @PreAuthorize("hasAnyAuthority('add:brand')")
    void addBrand(@RequestBody Brand brand) {
        brandService.saveOrUpdate(brand);
    }

    @PutMapping("")
    @Operation(summary = "Update brand")
    @SecurityRequirement(name = "bearerAuth", scopes = {"update:brand"})
    @PreAuthorize("hasAuthority('update:brand')")
    void updateBrand(@RequestBody Brand brand) {
        brandService.saveOrUpdate(brand);
    }

    @DeleteMapping("")
    @Operation(summary = "Delete brand", description = "Delete brand")
    @SecurityRequirement(name = "bearerAuth", scopes = {"delete:brand"})
    @PreAuthorize("hasAnyAuthority('delete:brand')")
    void deleteBrand(@RequestBody Brand brand) {
        brandService.remove(brand);
    }
}
