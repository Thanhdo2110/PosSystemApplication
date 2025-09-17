package com.example.possystem.mapper;

import com.example.possystem.modal.User;
import com.example.possystem.payload.dto.UserDTO;

public class UserMapper {

    public static UserDTO toDTO(User savedUser){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(savedUser.getId());
        userDTO.setFullname(savedUser.getFullname());
        userDTO.setEmail(savedUser.getEmail());
        userDTO.setRole(savedUser.getRole());
        userDTO.setCreatedAt(savedUser.getCreatedAt());
        userDTO.setUpdatedAt(savedUser.getUpdatedAt());
        userDTO.setLastLogin(savedUser.getLastLogin());
        userDTO.setPhone(savedUser.getPhone());

        return userDTO;
    }
}
