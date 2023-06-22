package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;

import java.util.List;

public interface ModelService {
    ModelDTO findById(String id) throws Exception;
    void saveOrUpdate(Model model);
    void remove(Model model);
    List<ModelDTO> findAll();
    List<ModelDTO> findAllByBrand(String brand);

    List<ModelDTO> findAllByBrandAndName(String brand, String name);

    ModelDTO findAllByBrandAndNameAndYear(String brand, String name, Integer year);

    void add(String brandName, String name, Integer year, List<String> typeNames);

    void remove(String brand, String name, Integer year);
    List<ModelDTO> searchModel(String brand, String name, Integer yearMin, Integer yearMax, String type, Integer page, Integer pageSize);
}
