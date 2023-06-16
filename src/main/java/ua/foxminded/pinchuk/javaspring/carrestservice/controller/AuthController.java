package ua.foxminded.pinchuk.javaspring.carrestservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.foxminded.pinchuk.javaspring.carrestservice.controller.payload.request.LoginRequest;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.AuthenticationService;

@RestController
public class AuthController {

    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    ResponseEntity login(@RequestBody LoginRequest request) throws Exception {
        return authService.login(request);
    }
}
