package com.example.possystem.repository;

import com.example.possystem.modal.Store;
import com.example.possystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User ,Long> {

    User findByEmail (String email);
    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);

}
