package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
