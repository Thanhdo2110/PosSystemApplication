package com.example.possystem.service;

import com.example.possystem.exceptions.UserException;
import com.example.possystem.payload.dto.UserDTO;
import com.example.possystem.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDTO userDTO) throws UserException;
    AuthResponse loign(UserDTO userDTO) throws UserException;
}
