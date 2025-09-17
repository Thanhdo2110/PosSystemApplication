package com.example.possystem.service;

import com.example.possystem.exceptions.UserException;
import com.example.possystem.modal.User;

import java.util.List;

public interface UserService {

    User getUserFromJwtTocken(String tocken) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException, Exception;
    User getUserById(Long id) throws UserException;
    List<User> getAllUsers();
}
