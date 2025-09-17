package com.example.possystem.service.impl;

import com.example.possystem.configuration.JwtProvider;
import com.example.possystem.exceptions.UserException;
import com.example.possystem.modal.User;
import com.example.possystem.repository.UserRepository;
import com.example.possystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtTocken(String tocken) throws UserException {

        String email = jwtProvider.getEmailFromTocken(tocken);
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("Invalid token");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UserException("user not found");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException , Exception {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new Exception("user not found");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(
                ()-> new UserException("User not found")
        );

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll() ;
    }
}
