package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    List<Model> getModelsByBrandNameIgnoreCase(String brand);
    List<Model> getModelsByBrandNameIgnoreCaseAndNameIgnoreCase(String brand, String name);
    Optional<Model>  getModelsByBrandNameIgnoreCaseAndNameIgnoreCaseAndYear(String brand,
                                                                            String name,
                                                                            Integer year);
    boolean existsById(String id);
}
