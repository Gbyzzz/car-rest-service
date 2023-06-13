package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
