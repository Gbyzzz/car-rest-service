package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.UserDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.UserMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.UserRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO findById(Long id) throws Exception {
        return userRepository.findById(id).map(userMapper).orElseThrow(
                () -> new Exception("User with id " + id +
                        " not found"));
    }

    @Override
    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper).collect(Collectors.toList());
    }

    @Override
    public User findByEmail(String email) throws Exception {
        return userRepository.findUserByEmailIgnoreCase(email).orElseThrow(
                () -> new Exception("User with email " + email +
                        "not found")
        );
    }
}
