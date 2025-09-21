package com.example.possystem.repository;

import com.example.possystem.modal.OrderItem;
import com.example.possystem.payload.dto.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository  extends JpaRepository<OrderItem,Long> {
}
