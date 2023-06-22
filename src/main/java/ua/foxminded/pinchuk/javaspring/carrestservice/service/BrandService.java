package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.dto.BrandDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;

import java.util.List;

public interface BrandService {
    BrandDTO findById(Long id) throws Exception;
    void saveOrUpdate(Brand brand);
    void remove(Brand brand);
    List<BrandDTO> findAll();

    Brand findByName(String brandName);
}
