package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.controller.payload.request.LoginRequest;
import ua.foxminded.pinchuk.javaspring.carrestservice.controller.payload.response.JwtResponse;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;
import ua.foxminded.pinchuk.javaspring.carrestservice.security.jwt.JwtUtils;
import ua.foxminded.pinchuk.javaspring.carrestservice.security.services.UserDetailsImpl;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.AuthenticationService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder encoder;
    private final UserService userService;

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(PasswordEncoder encoder, UserService userService,
                                     JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.encoder = encoder;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity login(LoginRequest request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userService.findById(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, user));
    }
}
