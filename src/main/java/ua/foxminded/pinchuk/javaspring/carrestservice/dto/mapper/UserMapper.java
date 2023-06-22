package ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper;

import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.UserDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;

import java.util.function.Function;

@Service
public class UserMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(user.getUserId(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getUserRole().name(), user.getPhone());
    }
}
