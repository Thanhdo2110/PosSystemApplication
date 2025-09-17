package com.example.possystem.payload.dto;

import com.example.possystem.domain.StoreStatus;
import com.example.possystem.modal.StoreContact;
import com.example.possystem.modal.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class StoreDTO {

        private Long id;

        private String brand;

        private UserDTO storeAdmin;

        private LocalDateTime createdAt;
        private  LocalDateTime updatedAt;

        private String description;

        private String storeType;

        private StoreStatus status;

        private StoreContact contact ;

}
