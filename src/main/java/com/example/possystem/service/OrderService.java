package com.example.possystem.service;

import com.example.possystem.domain.OrderStatus;
import com.example.possystem.domain.PaymentType;
import com.example.possystem.payload.dto.OrderDTO;

import java.util.List;

public interface OrderService  {
    OrderDTO createOrder(OrderDTO orderDTO)throws Exception;
    OrderDTO getOrderById(Long id) throws Exception;
    List<OrderDTO> getOrderByBranch(Long branchId,
                                    Long customerId,
                                    Long cashierId,
                                    PaymentType paymentType,
                                    OrderStatus status) throws Exception;
    List<OrderDTO> getOrderByCashier(Long cashierId);
    void deleteOrder(Long id)throws  Exception;
    List<OrderDTO> getTodayOrderByBranch(Long branchId) throws Exception;
    List<OrderDTO> getOrdersByCustomerId(Long customerId)throws Exception;
    List<OrderDTO> getTop5RecentOrderByBranchId(Long branchId) throws Exception;
}
