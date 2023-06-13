package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;

import java.util.List;

public interface TypeService {
    Type findById(int id) ;
    void saveOrUpdate(Type type);
    void removeCourse(Type type);
    List<Type> findAll();
}
