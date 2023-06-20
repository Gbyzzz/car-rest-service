package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    List<Type> getAllTypes(){
        return typeService.findAll();
    }

    @GetMapping("/{id}")
    Type getTypeById(@PathVariable Long id) throws Exception {
        return typeService.findById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void addType(@RequestBody Type type) throws Exception {
        typeService.saveOrUpdate(type);
    }

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void saveType(@RequestBody Type type) throws Exception {
        typeService.saveOrUpdate(type);
    }

    @DeleteMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    void deleteType(@RequestBody Type type) throws Exception {
        typeService.remove(type);
    }
}
