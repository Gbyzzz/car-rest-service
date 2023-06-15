package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    List<Model> getModelsByBrandNameIgnoreCase(String brand);
    List<Model> getModelsByBrandNameIgnoreCaseAndNameIgnoreCase(String brand, String name);
    Model getModelsByBrandNameIgnoreCaseAndNameIgnoreCaseAndYear(String brand, String name, Integer year);
}
