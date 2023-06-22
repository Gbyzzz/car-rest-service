package ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper;

import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ModelMapper implements Function<Model, ModelDTO> {
    @Override
    public ModelDTO apply(Model model) {
        return new ModelDTO(model.getId(), model.getBrand().getName(),
                model.getYear(), model.getName(), model.getTypes().stream().map(Type::getName).collect(Collectors.toList()));
    }
}
