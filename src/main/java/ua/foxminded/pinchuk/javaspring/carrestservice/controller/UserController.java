package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Integer id) throws Exception {
        return userService.findById(id);
    }

    @PostMapping ("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    void addUser(@RequestBody User user){
        userService.saveOrUpdate(user);
    }

    @PutMapping ("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    void saveUser(@RequestBody User user){
        userService.saveOrUpdate(user);
    }

    @DeleteMapping ("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    void deleteUser(@RequestBody User user){
        userService.remove(user);
    }
}
