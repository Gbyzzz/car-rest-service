package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;

import java.util.List;

public interface ModelService {
    Model findById(String id) throws Exception;
    void saveOrUpdate(Model model);
    void remove(Model model);
    List<Model> findAll();
    List<Model> findAllByBrand(String brand);

    List<Model> findAllByBrandAndName(String brand, String name);

    Model findAllByBrandAndNameAndYear(String brand, String name, Integer year);

    void add(String brandName, String name, Integer year, List<String> typeNames);

    void remove(String brand, String name, Integer year);
    List<Model> searchModel(String brand, String name, Integer yearMin, Integer yearMax, String type, Integer page, Integer pageSize);
}
