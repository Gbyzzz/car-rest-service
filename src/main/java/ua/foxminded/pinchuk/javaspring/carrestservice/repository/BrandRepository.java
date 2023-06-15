package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Brand findBrandByName(String brandName);

}
