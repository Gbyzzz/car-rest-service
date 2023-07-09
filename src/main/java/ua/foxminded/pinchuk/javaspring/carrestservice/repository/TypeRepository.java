package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findTypeByName(String typeName);
    List<Type> findAllByOrderByIdAsc();

}
