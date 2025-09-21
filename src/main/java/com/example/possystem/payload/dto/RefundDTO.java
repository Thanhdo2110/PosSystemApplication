package com.example.possystem.payload.dto;

import com.example.possystem.domain.PaymentType;
import com.example.possystem.modal.Branch;
import com.example.possystem.modal.Order;
import com.example.possystem.modal.ShiftReport;
import com.example.possystem.modal.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RefundDTO {

    private Long id;

    private OrderDTO order;
    private Long orderId;

    private  String reason;

    private Double amount;

//    private ShiftReport shiftReport;
    private Long shiftReportId;

    private UserDTO cashier;
    private String cashierName;

    private BranchDTO branch;
    private Long branchId;

    private PaymentType paymentType;

    private LocalDateTime createdAt;
}
