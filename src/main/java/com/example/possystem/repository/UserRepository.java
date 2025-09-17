package com.example.possystem.repository;

import com.example.possystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User ,Long> {

    User findByEmail (String email);
}
