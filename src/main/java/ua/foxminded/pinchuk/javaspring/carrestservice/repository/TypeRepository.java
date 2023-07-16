package ua.foxminded.pinchuk.javaspring.carrestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    Optional<Type> findTypeByNameIgnoreCase(String typeName);
    List<Type> findAllByOrderByIdAsc();
    void removeTypeByNameIgnoreCase(String name);
    boolean existsTypeByNameIgnoreCase(String name);
}
