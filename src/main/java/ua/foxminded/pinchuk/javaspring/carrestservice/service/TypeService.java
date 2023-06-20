package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;

import java.util.List;

public interface TypeService {
    Type findById(Long id) throws Exception;
    void saveOrUpdate(Type type);
    void remove(Type type);
    List<Type> findAll();

    Type findByName(String typeName);
}
