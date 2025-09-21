package com.example.possystem.repository;

import com.example.possystem.modal.Order;
import com.example.possystem.modal.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomerId(Long customerId);

    List<Order> findByBranchId(Long branchId);

    List<Order> findByCashierId(Long cashierId);

    List<Order> findByBranchIdAndCreatedAtBetween(Long branchId,
                                                  LocalDateTime from,
                                                  LocalDateTime to);

    List<Order> findByCashierAndCreatedAtBetween(User cashier,
                                                 LocalDateTime from,
                                                 LocalDateTime to);

    List<Order> findTop5ByBranchIdOrderByCreatedAtDesc(Long branchId);


}
