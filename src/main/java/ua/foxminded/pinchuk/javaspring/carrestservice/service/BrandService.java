package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.dto.BrandDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemAlreadyExists;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemNotFoundException;

import java.util.Set;

public interface BrandService {
    BrandDTO findById(Long id) throws Exception;
    void update(BrandDTO brandDTO);
    void removeByName(String name);
    Set<BrandDTO> findAll();
    Brand findByName(String brandName) throws ItemNotFoundException;
    Brand add(String name) throws ItemAlreadyExists;
    boolean brandExistsByName(String name);
}
