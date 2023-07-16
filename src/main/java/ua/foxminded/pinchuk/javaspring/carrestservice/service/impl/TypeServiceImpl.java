package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.TypeDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.TypeMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.TypeRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.TypeService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemAlreadyExists;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    public TypeServiceImpl(TypeRepository typeRepository, TypeMapper typeMapper) {
        this.typeRepository = typeRepository;
        this.typeMapper = typeMapper;
    }

    @Override
    public TypeDTO findById(Long id) throws ItemNotFoundException {
        return typeRepository.findById(id).map(typeMapper).orElseThrow(
                () -> new ItemNotFoundException("Type with id " + id +
                        " not found"));
    }

    @Override
    @Transactional
    public void update(TypeDTO typeDTO) {
        typeRepository.save(typeMapper.apply(typeDTO));
    }

    @Override
    @Transactional
    public void removeByName(String name) {
        typeRepository.removeTypeByNameIgnoreCase(name);
    }

    @Override
    public Set<TypeDTO> findAll() {
        return typeRepository.findAllByOrderByIdAsc()
                .stream().map(typeMapper).collect(Collectors.toSet());
    }

    @Override
    public Type findByName(String typeName) throws ItemNotFoundException {
        return typeRepository.findTypeByNameIgnoreCase(typeName).orElseThrow(
                () -> new ItemNotFoundException("Type with name " + typeName +
                        " not found")
        );
    }

    @Override
    public Type add(String name) throws ItemAlreadyExists {
        if (typeExistsName(name)) {
            throw new ItemAlreadyExists("Type with name " + name + " already exists in db");
        }
        return typeRepository.save(new Type(name));
    }

    @Override
    public boolean typeExistsName(String name) {
        return typeRepository.existsTypeByNameIgnoreCase(name);
    }
}
