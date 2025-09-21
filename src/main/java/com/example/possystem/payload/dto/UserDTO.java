package com.example.possystem.payload.dto;

import com.example.possystem.domain.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private UserRole role;

    private String password;

    private Long branchId;
    private Long storeId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}
