package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.BrandDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.BrandMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.BrandRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.BrandService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public BrandDTO findById(Long id) throws Exception {
        return brandRepository.findById(id).map(brandMapper).orElseThrow(
                ()->new Exception("Brand with id " + id + " not found"));
    }

    @Override
    public void saveOrUpdate(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void removeByName(String name) {
        brandRepository.removeBrandByNameIgnoreCase(name);
    }

    @Override
    public Set<BrandDTO> findAll() {
        return brandRepository.findAllByOrderByIdAsc().stream().map(brandMapper)
                .collect(Collectors.toSet());
    }

    @Override
    public Brand findByName(String brandName) {
        return brandRepository.findBrandByName(brandName);
    }
}
