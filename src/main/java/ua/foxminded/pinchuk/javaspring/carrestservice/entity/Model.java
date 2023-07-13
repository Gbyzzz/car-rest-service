package ua.foxminded.pinchuk.javaspring.carrestservice.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "model",
        uniqueConstraints=
@UniqueConstraint(
        columnNames={"brand_id", "year", "name"}))
public class Model {

    @Id
    @Column(name = "id", length = 10)
    private String id;
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @OneToMany
    @JoinTable(name = "car_model_type",
            joinColumns = {@JoinColumn(name = "model_id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id")})
    private List<Type> types;

    public Model() {
    }

    public Model(String id, Brand brand, Integer year, String name, List<Type> types) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.name = name;
        this.types = types;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(id, model.id) && Objects.equals(brand, model.brand) && Objects.equals(year, model.year) && Objects.equals(name, model.name) && Objects.equals(types, model.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, year, name, types);
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", brand=" + brand +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", types=" + types +
                '}';
    }
}
