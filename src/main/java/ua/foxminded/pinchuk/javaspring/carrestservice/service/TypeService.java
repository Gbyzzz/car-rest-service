package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.dto.TypeDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemAlreadyExists;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemNotFoundException;

import java.util.Set;

public interface TypeService {
    TypeDTO findById(Long id) throws Exception;
    void update(TypeDTO typeDTO);
    void removeByName(String name);
    Set<TypeDTO> findAll();
    Type findByName(String typeName) throws ItemNotFoundException;
    Type add(String name) throws ItemAlreadyExists;
    boolean typeExistsName(String name);
}
