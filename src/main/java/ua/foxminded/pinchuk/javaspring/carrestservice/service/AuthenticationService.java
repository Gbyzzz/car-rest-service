package ua.foxminded.pinchuk.javaspring.carrestservice.service;

import org.springframework.http.ResponseEntity;
import ua.foxminded.pinchuk.javaspring.carrestservice.controller.payload.request.LoginRequest;

public interface AuthenticationService {
    ResponseEntity login(LoginRequest request) throws Exception;
}
