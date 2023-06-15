package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    Type findTypeByName(String typeName);

}
