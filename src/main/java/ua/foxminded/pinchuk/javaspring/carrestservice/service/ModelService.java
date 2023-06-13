package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;

import java.util.List;

public interface ModelService {
    Model findById(String id) ;
    void saveOrUpdate(Model car);
    void removeCourse(Model car);
    List<Model> findAll();
}
