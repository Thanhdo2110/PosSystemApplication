package com.example.possystem.service.impl;

import com.example.possystem.domain.UserRole;
import com.example.possystem.mapper.UserMapper;
import com.example.possystem.modal.Branch;
import com.example.possystem.modal.Store;
import com.example.possystem.modal.User;
import com.example.possystem.payload.dto.UserDTO;
import com.example.possystem.repository.BranchRepository;
import com.example.possystem.repository.StoreRepository;
import com.example.possystem.repository.UserRepository;
import com.example.possystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private PasswordEncoder passwordEncoder;


    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;

    @Override
    public UserDTO createStoreEmployee(UserDTO employee, Long storeId) throws Exception {
        Store store=storeRepository.findById(storeId).orElseThrow(
                ()->new Exception("Store not found")
        );
        Branch branch = null;
        if (employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            if (employee.getBranchId()==null){
                throw  new Exception("branch id is required to create branch manager");
            }
            branch = branchRepository.findById(employee.getBranchId()).orElseThrow(
                    ()-> new Exception("branch not found")
            );
        }
        User user= UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User savedEmployee = userRepository.save(user);
        if (employee.getRole()==UserRole.ROLE_BRANCH_MANAGER && branch!=null){
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }
        return UserMapper.toDTO(savedEmployee);
    }

    @Override
    public UserDTO createBranchEmployee(UserDTO employee, Long branchId) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(
                ()-> new Exception("branch not found")
        );
        if (employee.getRole()==UserRole.ROLE_BRANCH_CASHIER ||
        employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            User user = UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
            return UserMapper.toDTO(userRepository.save(user));
        }
        throw new Exception("branch role not supported");
    }

    @Override
    public User updateEmployee(Long employeeId, UserDTO employeeDetails) throws Exception {
        User existingEmployee=userRepository.findById(employeeId).orElseThrow(
                ()-> new Exception("employee not exist with given id")
        );

        Branch branch=branchRepository.findById(employeeDetails.getBranchId())
                        .orElseThrow(()->new Exception("Branch not found"));

        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setFullname(employeeDetails.getFullname());
        existingEmployee.setPassword(employeeDetails.getPassword());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setBranch(branch);
        return userRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User employee=userRepository.findById(employeeId).orElseThrow(
                ()-> new Exception("employee not found")
        );
        userRepository.delete(employee);

    }

    @Override
    public List<UserDTO> findStoreEmployee(Long storeId, UserRole role) throws Exception {
        Store store=storeRepository.findById(storeId).orElseThrow(
                ()->new Exception("Store not found")
        );

        return userRepository.findByStore(store).stream()
                .filter(user -> role==null || user.getRole()==role)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findBranchEnloyees(Long branchId, UserRole role) throws Exception {
        Branch branch=branchRepository.findById(branchId).orElseThrow(
                ()-> new Exception("branch not found")
        );
        return userRepository.findByBranchId(branchId)
        .stream().filter(
            user -> role==null || user.getRole()==role
        ).map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
