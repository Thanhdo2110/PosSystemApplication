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
        userDTO.setBranchId(savedUser.getBranch()!=null?savedUser.getBranch().getId():null);
        userDTO.setStoreId(savedUser.getStore()!=null?savedUser.getStore().getId():null);

        return userDTO;
    }
    public static User toEntity(UserDTO userDTO){
        User createUser=new User();
//
        createUser.setEmail(userDTO.getEmail());
        createUser.setFullname(userDTO.getFullname());
        createUser.setRole(userDTO.getRole());
        createUser.setCreatedAt(userDTO.getCreatedAt());
        createUser.setUpdatedAt(userDTO.getUpdatedAt());
        createUser.setLastLogin(userDTO.getLastLogin());
        createUser.setPhone(userDTO.getPhone());
        createUser.setPassword(userDTO.getPassword());

        return createUser;
    }
}
