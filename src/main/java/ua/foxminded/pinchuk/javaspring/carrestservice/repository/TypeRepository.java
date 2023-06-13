package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
