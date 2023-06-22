package ua.foxminded.pinchuk.javaspring.carrestservice.dto;

public record CarDTO(
        Long id,
        String brandName,

        String modelName,
        String type,
        String carColor,
        String carPlate
) {
}
