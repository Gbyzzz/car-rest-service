package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.TypeDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.TypeMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.TypeRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.TypeService;

import java.util.List;
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
    public TypeDTO findById(Long id) throws Exception {
        return typeRepository.findById(id).map(typeMapper).orElseThrow(
                () -> new Exception("Type with id " + id +
                        " not found"));
    }

    @Override
    public void saveOrUpdate(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void remove(Type type) {
        typeRepository.delete(type);
    }

    @Override
    public List<TypeDTO> findAll() {
        return typeRepository.findAll().stream().map(typeMapper).collect(Collectors.toList());
    }

    @Override
    public Type findByName(String typeName) {
        return typeRepository.findTypeByName(typeName);
    }
}
