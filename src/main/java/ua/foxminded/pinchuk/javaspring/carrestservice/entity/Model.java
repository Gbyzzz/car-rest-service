package ua.foxminded.pinchuk.javaspring.carrestservice.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "model")
public class Model {

    @Id
    @Column(name = "id")
    private String id;
    @ManyToOne
    @Column(name = "brand_id")
    private Brand brand;
    @Column(name = "year")
    private Integer year;
    @Column(name = "name")
    private String name;

    public Model() {
    }

    public Model(String id, Brand brand, Integer year, String name) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(id, model.id) && Objects.equals(brand, model.brand) && Objects.equals(year, model.year) && Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, year, name);
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", brand=" + brand +
                ", year=" + year +
                ", name='" + name + '\'' +
                '}';
    }
}
