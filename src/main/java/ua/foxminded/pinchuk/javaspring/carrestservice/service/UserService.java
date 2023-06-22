package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.dto.UserDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;

import java.util.List;

public interface UserService {
    UserDTO findById(Long id) throws Exception;
    void saveOrUpdate(User user);
    void remove(User user);
    List<UserDTO> findAll();

    User findByEmail(String email) throws Exception;
}
