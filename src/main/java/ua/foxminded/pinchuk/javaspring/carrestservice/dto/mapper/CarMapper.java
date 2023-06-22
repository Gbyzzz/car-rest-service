package ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper;

import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.CarDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Car;

import java.util.function.Function;

@Service
public class CarMapper implements Function<Car, CarDTO> {
    @Override
    public CarDTO apply(Car car) {
        return new CarDTO(car.getId(),car.getCarModelType().getModel().getBrand().getName(),  car.getCarModelType().getModel().getName(),
                car.getCarModelType().getType().getName(), car.getCarColor(), car.getCarPlate());
    }
}
