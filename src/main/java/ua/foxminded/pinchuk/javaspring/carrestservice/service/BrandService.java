package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand findById(int id) ;
    void saveOrUpdate(Brand brand);
    void removeCourse(Brand brand);
    List<Brand> findAll();
}
