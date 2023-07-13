package ua.foxminded.pinchuk.javaspring.carrestservice.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car_model_type",
        uniqueConstraints=
        @UniqueConstraint(
                columnNames={"model_id", "type_id"}))
public class CarModelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @OneToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    public CarModelType() {
    }

    public CarModelType(Long id, Model model, Type type) {
        this.id = id;
        this.model = model;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModelType that = (CarModelType) o;
        return Objects.equals(id, that.id) && Objects.equals(model, that.model) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, type);
    }

    @Override
    public String toString() {
        return "CarModelType{" +
                "id=" + id +
                ", model=" + model +
                ", type=" + type +
                '}';
    }
}
