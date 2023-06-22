package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.dto.TypeDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;

import java.util.List;

public interface TypeService {
    TypeDTO findById(Long id) throws Exception;
    void saveOrUpdate(Type type);
    void remove(Type type);
    List<TypeDTO> findAll();

    Type findByName(String typeName);
}
