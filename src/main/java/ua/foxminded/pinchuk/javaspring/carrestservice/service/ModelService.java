package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ServiceException;

import java.util.List;

public interface ModelService {
    ModelDTO findById(String id) throws Exception;
    void remove(Model model);
    List<ModelDTO> findAll();

    ModelDTO findAllByBrandAndNameAndYear(String brand, String name, Integer year) throws ServiceException;

    ModelDTO add(String brandName, String name, Integer year, List<String> typeNames) throws ServiceException;
    ModelDTO update(ModelDTO modelDTO) throws ServiceException;

    void remove(String brand, String name, Integer year) throws ServiceException;
    List<ModelDTO> searchModel(String brand, String name, Integer yearMin, Integer yearMax, String type, Integer page, Integer pageSize) throws ServiceException;
}
