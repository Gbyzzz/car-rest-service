package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.BrandRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.BrandService;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findById(Long id) throws Exception {
        return brandRepository.findById(id).orElseThrow(
                ()->new Exception("Brand with id " + id + " not found"));
    }

    @Override
    public void saveOrUpdate(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void remove(Brand brand) {
        brandRepository.delete(brand);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findByName(String brandName) {
        return brandRepository.findBrandByName(brandName);
    }
}
