package com.example.possystem.repository;

import com.example.possystem.modal.Refund;
import com.example.possystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository  extends JpaRepository<Refund,Long> {

    List<Refund> findByCashierIdAndCreatedAtBetween(
            Long cashier,
            LocalDateTime from ,
            LocalDateTime to
    );
    List<Refund> findByCashierId(Long id);
    List<Refund> findByShiftReportId(Long id);
    List<Refund> findByBranchId(Long id);
}
