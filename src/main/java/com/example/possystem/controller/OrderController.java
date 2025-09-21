package com.example.possystem.controller;

import com.example.possystem.domain.OrderStatus;
import com.example.possystem.domain.PaymentType;
import com.example.possystem.payload.dto.OrderDTO;
import com.example.possystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(
            @RequestBody OrderDTO order
    ) throws Exception {
        return ResponseEntity.ok(orderService.createOrder(order));
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDTO>> getOrderBranchId(
            @PathVariable Long branchId,
            @RequestParam(required = false)Long customerId,
            @RequestParam(required = false)Long cashierId,
            @RequestParam(required = false)PaymentType paymentType,
            @RequestParam(required = false)OrderStatus orderStatus
            ) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByBranch(branchId,
                customerId,cashierId,paymentType ,orderStatus));
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<OrderDTO>> getOrderByCashier(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByCashier(id));
    }

    @GetMapping("/today/branch/{id}")
    public ResponseEntity<List<OrderDTO>> getTodayOrder(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrderByBranch(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDTO>> getCustomersOrder(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(id));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDTO>> getRecentOrder(
            @PathVariable Long branchId
    ) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrderByBranchId(branchId));
    }


}
