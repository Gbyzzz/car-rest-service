package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.dto.CarDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Car;

import java.util.List;

public interface CarService {

    CarDTO findById(Long id) throws Exception;
    void saveOrUpdate(Car car);
    void remove(Car car);
    List<Car> findAll();

    List<CarDTO> searchCar(String brandName, Integer yearMin, Integer yearMax,
                           String type, String color, String modelName, Integer page,
                           Integer pageSize);
}
