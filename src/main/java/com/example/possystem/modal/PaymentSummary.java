package com.example.possystem.modal;


import com.example.possystem.domain.PaymentType;
import lombok.Data;

@Data
public class PaymentSummary {

    private PaymentType type;
    private Double totalAmount;
    private int transactionCount;
    private double percentage;
}
