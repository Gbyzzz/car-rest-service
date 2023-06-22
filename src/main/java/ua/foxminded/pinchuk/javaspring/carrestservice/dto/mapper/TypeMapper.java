package ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper;

import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.TypeDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;

import java.util.function.Function;

@Service
public class TypeMapper implements Function<Type, TypeDTO> {
    @Override
    public TypeDTO apply(Type type) {
        return new TypeDTO(type.getId(), type.getName());
    }
}
