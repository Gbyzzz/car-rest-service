package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.BrandDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.BrandMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.BrandRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.BrandService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ServiceException;

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
    public BrandDTO findById(Long id) throws ServiceException {
        return brandRepository.findById(id).map(brandMapper).orElseThrow(
                ()->new ServiceException("Brand with id " + id + " not found"));
    }

    @Override
    @Transactional
    public void update(BrandDTO brandDTO) {
        brandRepository.save(brandMapper.apply(brandDTO));
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
    public Brand findByName(String brandName) throws ServiceException {
        return brandRepository.findBrandByNameIgnoreCase(brandName).orElseThrow(
                ()->new ServiceException("Brand with name " + brandName + " not found"));
    }

    @Override
    public Brand add(String name) throws ServiceException {
        if(brandExistsByName(name)){
            throw new ServiceException("Brand with name " + name + " already exists in db");
        }
       return brandRepository.save(new Brand(name));
    }

    @Override
    public boolean brandExistsByName(String name) {
        return brandRepository.existsBrandByNameIgnoreCase(name);
    }
}
