package com.example.possystem.mapper;

import com.example.possystem.modal.OrderItem;
import com.example.possystem.payload.dto.OrderItemDTO;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem item){
        if (item==null) return null;
        return OrderItemDTO.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .product(ProductMapper.toDTO(item.getProduct()))
                .build();

    }
}
