package com.example.possystem.payload.response;


import com.example.possystem.payload.dto.UserDTO;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDTO user;
}
