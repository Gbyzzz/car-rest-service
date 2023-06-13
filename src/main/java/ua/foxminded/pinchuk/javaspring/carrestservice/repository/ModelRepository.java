package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;

public interface ModelRepository extends JpaRepository<Model, String> {
}
