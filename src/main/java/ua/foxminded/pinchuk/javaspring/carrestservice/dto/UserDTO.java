package ua.foxminded.pinchuk.javaspring.carrestservice.dto;

import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;

public record UserDTO(
        Long id,
        String email,
        String firstName,
        String lastName,
        String userRole,
        String phone
) {
}
