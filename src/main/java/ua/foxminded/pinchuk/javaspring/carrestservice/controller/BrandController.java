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
    @Operation(summary = "Get all brands", description = "Get all brands from db")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = BrandDTO.class))))
    List<BrandDTO> getAllBrands() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get brand by id",
            description = "Get brand by id from db. Brand must exist")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BrandDTO.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error. Not valid id",
            content = @Content())
    BrandDTO getBrandById(@PathVariable Long id) throws Exception {
        return brandService.findById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('add:brand')")
    @Operation(summary = "Add brand", description = "Add brand to db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"add:brand"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = {@Content()})
    void addBrand(@RequestBody Brand brand) {
        brandService.saveOrUpdate(brand);
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('update:brand')")
    @Operation(summary = "Update brand", description = "Update existing brand in db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"update:brand"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = {@Content()})
    void updateBrand(@RequestBody Brand brand) {
        brandService.saveOrUpdate(brand);
    }

    @DeleteMapping("")
    @PreAuthorize("hasAuthority('delete:brand')")
    @Operation(summary = "Delete brand", description = "Delete existing brand from db")
    @SecurityRequirement(name = "bearerAuth", scopes = {"delete:brand"})
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = {@Content()})
    void deleteBrand(@RequestBody Brand brand) {
        brandService.remove(brand);
    }
}
