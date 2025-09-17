package com.example.possystem.service.impl;

import com.example.possystem.configuration.JwtProvider;
import com.example.possystem.domain.UserRole;
import com.example.possystem.exceptions.UserException;
import com.example.possystem.mapper.UserMapper;
import com.example.possystem.modal.User;
import com.example.possystem.payload.dto.UserDTO;
import com.example.possystem.payload.response.AuthResponse;
import com.example.possystem.repository.UserRepository;
import com.example.possystem.service.AuthService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomerUserImplementation customerUserImplementation;

    @Override
    public AuthResponse signup(UserDTO userDTO) throws UserException {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user != null){
            throw new UserException("Email id already registered !");
        }
        if (userDTO.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new UserException("role admin is not allowed !");
        }

        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRole(userDTO.getRole());
        newUser.setFullname(userDTO.getFullname());
        newUser.setPhone(userDTO.getPhone());
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());

        newUser.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(newUser);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(),userDTO.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.genarateTocken(authentication);

        AuthResponse authResponse =new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setUser(UserMapper.toDTO(savedUser));

        return authResponse;
    }

    @Override
    public AuthResponse loign(UserDTO userDTO) throws UserException {
        String email= userDTO.getEmail();
        String password =userDTO.getPassword();

        Authentication authentication = authenticate(email,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String role = authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.genarateTocken(authentication);

        User user = userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse authResponse =new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successfully");
        authResponse.setUser(UserMapper.toDTO(user));

        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {

        UserDetails userDetails = customerUserImplementation.loadUserByUsername(email);

        if(userDetails == null){
            throw new UserException("email id doesn't exist"+email);
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new UserException("password doesn't match");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}
