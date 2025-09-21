package com.example.possystem.payload.dto;

import com.example.possystem.domain.OrderStatus;
import com.example.possystem.domain.PaymentType;
import com.example.possystem.modal.Branch;
import com.example.possystem.modal.Customer;
import com.example.possystem.modal.OrderItem;
import com.example.possystem.modal.User;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    private Long branchId;
    private Long customerId;

    private BranchDTO branch;

    private UserDTO cashier;

    private Customer customer;

    private PaymentType paymentType;

    private List<OrderItemDTO> items;
}
