package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Car;

import java.util.List;

public interface CarService {

    Car findById(int id) throws Exception;
    void saveOrUpdate(Car car);
    void remove(Car car);
    List<Car> findAll();

    List<Car> searchCar(String brandName, Integer yearMin, Integer yearMax,
                        String type, String color, String modelName, Integer page,
                        Integer pageSize);
}
