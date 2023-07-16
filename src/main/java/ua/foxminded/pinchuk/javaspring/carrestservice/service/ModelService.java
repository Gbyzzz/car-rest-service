package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemAlreadyExists;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemNotFoundException;

import java.util.List;

public interface ModelService {
    ModelDTO findById(String id) throws Exception;
    void saveOrUpdate(Model model);
    void remove(Model model);
    List<ModelDTO> findAll();

    ModelDTO findAllByBrandAndNameAndYear(String brand, String name, Integer year) throws ItemNotFoundException;

    void add(String brandName, String name, Integer year, List<String> typeNames) throws ItemNotFoundException, ItemAlreadyExists;

    void remove(String brand, String name, Integer year) throws ItemNotFoundException;
    List<ModelDTO> searchModel(String brand, String name, Integer yearMin, Integer yearMax, String type, Integer page, Integer pageSize) throws ItemNotFoundException;
}
