package com.example.possystem.controller;


import com.example.possystem.exceptions.UserException;
import com.example.possystem.mapper.UserMapper;
import com.example.possystem.modal.User;
import com.example.possystem.payload.dto.UserDTO;
import com.example.possystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(
            @RequestHeader("Authorization") String jwt) throws UserException {
        User user=userService.getUserFromJwtTocken(jwt);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws UserException {
        User user=userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }
}
