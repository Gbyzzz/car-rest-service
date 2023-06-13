package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Car;

import java.util.List;

public interface CarService {

    Car findById(int id) ;
    void saveOrUpdate(Car car);
    void removeCourse(Car car);
    List<Car> findAll();
}
