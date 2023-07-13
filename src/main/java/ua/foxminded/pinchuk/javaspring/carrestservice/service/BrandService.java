package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.dto.BrandDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;

import java.util.Set;

public interface BrandService {
    BrandDTO findById(Long id) throws Exception;
    void saveOrUpdate(Brand brand);
    void removeByName(String name);
    Set<BrandDTO> findAll();

    Brand findByName(String brandName);
}
