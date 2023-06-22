package ua.foxminded.pinchuk.javaspring.carrestservice.dto;

import java.util.List;

public record ModelDTO(
        String id,
        String brand,
        Integer year,
        String name,
        List<String> types

        ) {
}
