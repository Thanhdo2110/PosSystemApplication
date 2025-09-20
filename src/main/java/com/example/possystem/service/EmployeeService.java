package com.example.possystem.service;

import com.example.possystem.domain.UserRole;
import com.example.possystem.modal.User;
import com.example.possystem.payload.dto.UserDTO;

import java.util.List;

public interface EmployeeService {

    UserDTO createStoreEmployee(UserDTO employee, Long storeId)throws Exception;
    UserDTO createBranchEmployee(UserDTO employee, Long branchId)throws Exception;
    User updateEmployee(Long employee,UserDTO employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<UserDTO> findStoreEmployee(Long storeId, UserRole role) throws Exception;
    List<UserDTO> findBranchEnloyees(Long branchId,UserRole role) throws Exception;
}
