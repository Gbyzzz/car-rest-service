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
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.TypeDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.TypeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/types")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("")
    @RouterOperation(operation = @Operation(summary = "getAllTypes",
            responses = @ApiResponse(responseCode = "200",
            content = @Content(
                    array = @ArraySchema(
                            arraySchema = @Schema(implementation = ModelDTO.class))))))
    List<TypeDTO> getAllTypes(){
        return typeService.findAll();
    }

    @GetMapping("/{id}")
    @RouterOperation(operation = @Operation(summary = "getTypeById",
            responses = @ApiResponse(responseCode = "200", content = @Content(
                    schema = @Schema(implementation = TypeDTO.class)))))
    TypeDTO getTypeById(@PathVariable Long id) throws Exception {
        return typeService.findById(id);
    }

    @PostMapping("")
    @RouterOperation(operation = @Operation(summary = "addType",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void addType(@RequestBody Type type) throws Exception {
        typeService.saveOrUpdate(type);
    }

    @PutMapping("")
    @RouterOperation(operation = @Operation(summary = "saveType",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void saveType(@RequestBody Type type) throws Exception {
        typeService.saveOrUpdate(type);
    }

    @DeleteMapping("")
    @RouterOperation(operation = @Operation(summary = "deleteType",
            security = @SecurityRequirement(name = "bearerAuth",
                    scopes = {"ADMIN", "STAFF"})))
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void deleteType(@RequestBody Type type) throws Exception {
        typeService.remove(type);
    }
}
