package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
}
