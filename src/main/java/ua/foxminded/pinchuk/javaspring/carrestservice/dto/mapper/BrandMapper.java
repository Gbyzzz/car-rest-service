package ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper;

import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.BrandDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;

import java.util.function.Function;

@Service
public class BrandMapper implements Function<Brand, BrandDTO> {
    @Override
    public BrandDTO apply(Brand brand) {
        return new BrandDTO(brand.getId(), brand.getName());
    }
}
