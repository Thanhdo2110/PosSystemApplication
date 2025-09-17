package com.example.possystem.controller;

import com.example.possystem.exceptions.UserException;
import com.example.possystem.payload.dto.UserDTO;
import com.example.possystem.payload.response.AuthResponse;
import com.example.possystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler (
            @RequestBody UserDTO userDTO)throws UserException {
        return ResponseEntity.ok(authService.signup(userDTO));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler (
            @RequestBody UserDTO userDTO)throws UserException {
        return ResponseEntity.ok(authService.loign(userDTO));
    }
}
