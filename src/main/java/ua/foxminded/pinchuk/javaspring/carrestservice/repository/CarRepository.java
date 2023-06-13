package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
