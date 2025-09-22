package com.example.possystem.repository;

import com.example.possystem.modal.ShiftReport;
import com.example.possystem.modal.User;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShiftReportRepository extends JpaRepository<ShiftReport,Long> {

    List<ShiftReport> findByCashierId(Long id);
    List<ShiftReport> findByBranchId(Long id);

    Optional<ShiftReport> findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(User cashier);

    Optional<ShiftReport> findByCashierAndShiftStartBetween(User cashier,
                                                            LocalDateTime start,
                                                            LocalDateTime end);
}
