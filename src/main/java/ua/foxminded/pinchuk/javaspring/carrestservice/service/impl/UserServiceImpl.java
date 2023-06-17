package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.UserRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(int id) throws Exception {
        return userRepository.findById(id).orElseThrow(
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
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) throws Exception {
        return userRepository.findUserByEmailIgnoreCase(email).orElseThrow(
                () -> new Exception("User with email " + email +
                        "not found")
        );
    }
}
