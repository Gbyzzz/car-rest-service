package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;

import java.util.List;

public interface UserService {
    User findById(int id) ;
    void saveOrUpdate(User user);
    void removeCourse(User user);
    List<User> findAll();
}
