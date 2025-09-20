package com.example.possystem.controller;

import com.example.possystem.domain.UserRole;
import com.example.possystem.modal.User;
import com.example.possystem.payload.dto.UserDTO;
import com.example.possystem.payload.response.ApiResponse;
import com.example.possystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ClientInfoStatus;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDTO> createStoreEmployee(
            @PathVariable Long storeId,
            @RequestBody UserDTO userDTO) throws Exception {
        UserDTO employee = employeeService.createStoreEmployee(userDTO,storeId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDTO> createBranchEmployee(
            @PathVariable Long branchId,
            @RequestBody UserDTO userDTO) throws Exception {
        UserDTO employee = employeeService.createBranchEmployee(userDTO,branchId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateEmployee(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) throws Exception {
        User employee = employeeService.updateEmployee(id,userDTO);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(
            @PathVariable Long id
           ) throws Exception {
        employeeService.deleteEmployee(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Employee delete successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<UserDTO>> storeEmployee(
            @PathVariable Long id,
            @RequestParam(required = false)UserRole userRole) throws Exception {
        List<UserDTO> employee = employeeService.findStoreEmployee(id,userRole);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/branch/{id}")
    public ResponseEntity<List<UserDTO>> branchEmployee(
            @PathVariable Long id,
            @RequestParam(required = false)UserRole userRole) throws Exception {
        List<UserDTO> employee = employeeService.findBranchEnloyees(id,userRole);
        return ResponseEntity.ok(employee);
    }


}
