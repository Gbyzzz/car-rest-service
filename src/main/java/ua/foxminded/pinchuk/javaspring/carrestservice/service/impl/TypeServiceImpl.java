package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.TypeRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.TypeService;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Type findById(int id) throws Exception {
        return typeRepository.findById(id).orElseThrow(
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
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type findByName(String typeName) {
        return typeRepository.findTypeByName(typeName);
    }
}
