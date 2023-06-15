package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
