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

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "car_model_type",
            joinColumns = {@JoinColumn(name = "car_model_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "model_id")})
    private Model model;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "car_model_type",
            joinColumns = {@JoinColumn(name = "car_model_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id")})
    private Type type;

    @Column(name = "car_color")
    private String carColor;

    @Column(name = "car_plate")
    private String carPlate;

    public Car() {
    }

    public Car(Integer id, Model model, Type type,
               String carColor, String carPlate) {
        this.id = id;
        this.model = model;
        this.type = type;
        this.carColor = carColor;
        this.carPlate = carPlate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        return Objects.equals(id, car.id) && Objects.equals(model, car.model) && Objects.equals(type, car.type) && Objects.equals(carColor, car.carColor) && Objects.equals(carPlate, car.carPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, type, carColor, carPlate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model=" + model +
                ", type=" + type +
                ", carColor='" + carColor + '\'' +
                ", carPlate='" + carPlate + '\'' +
                '}';
    }
}
