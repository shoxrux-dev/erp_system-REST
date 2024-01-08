package com.system.erp_system.service;

import com.system.erp_system.config.token.JwtService;
import com.system.erp_system.domain.*;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.UserRepository;
import com.system.erp_system.validation.CommonSchemaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final CommonSchemaValidator commonSchemaValidator;

    public User create(User user){
        commonSchemaValidator.userNotExist(user.getUsername());
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        Instant now = Instant.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User login(User user){
        User user1 = userRepository.findUserByUsername(user.getUsername()).orElseThrow(() -> new RecordNotFoundException(String.format("user not found wit username %s ", user.getUsername())));
        commonSchemaValidator.validatePassword(user1, user.getPassword());
        return user1;
    }

    public User getCurrentUser(String token) {
        String username = jwtService.extractUsername(token);
        return userRepository.findUserByUsername(username).orElseThrow(() -> new RecordNotFoundException(String.format("user not found %s username", username)));
    }

}
