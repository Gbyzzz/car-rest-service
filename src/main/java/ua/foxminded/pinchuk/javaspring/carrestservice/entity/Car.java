package ua.foxminded.pinchuk.javaspring.carrestservice.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "car_model_type_id")
    private CarModelType carModelType;
    @Column(name = "car_color", length = 15)
    private String carColor;

    @Column(name = "car_plate", length = 10, unique = true)
    private String carPlate;

    public Car() {
    }

    public Car(Long id, CarModelType carModelType, String carColor, String carPlate) {
        this.id = id;
        this.carModelType = carModelType;
        this.carColor = carColor;
        this.carPlate = carPlate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarModelType getCarModelType() {
        return carModelType;
    }

    public void setCarModelType(CarModelType carModelType) {
        this.carModelType = carModelType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(carModelType, car.carModelType) && Objects.equals(carColor, car.carColor) && Objects.equals(carPlate, car.carPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carModelType, carColor, carPlate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carModelType=" + carModelType +
                ", carColor='" + carColor + '\'' +
                ", carPlate='" + carPlate + '\'' +
                '}';
    }
}
