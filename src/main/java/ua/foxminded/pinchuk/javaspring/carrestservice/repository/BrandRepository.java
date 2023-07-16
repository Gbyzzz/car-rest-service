package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findBrandByNameIgnoreCase(String brandName);
    List<Brand> findAllByOrderByIdAsc();

    void removeBrandByNameIgnoreCase(String name);

    boolean existsBrandByNameIgnoreCase(String name);
}
