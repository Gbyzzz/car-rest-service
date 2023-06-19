package ua.foxminded.pinchuk.javaspring.carrestservice.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "car_model_type_id")
    private CarModelType carModelType;
    @Column(name = "car_color")
    private String carColor;

    @Column(name = "car_plate")
    private String carPlate;

    public Car() {
    }

    public Car(Integer id, CarModelType carModelType, String carColor, String carPlate) {
        this.id = id;
        this.carModelType = carModelType;
        this.carColor = carColor;
        this.carPlate = carPlate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
