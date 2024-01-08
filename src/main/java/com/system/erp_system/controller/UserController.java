package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.UserConverter;
import com.system.erp_system.domain.User;
import com.system.erp_system.dto.user.UserResponseDto;
import com.system.erp_system.util.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@SecurityRequirement(name = SwaggerConfig.BEARER)
@EnableMethodSecurity
public class UserController {
    @GetMapping
    @Operation(summary = "Get all sales")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    public UserResponseDto getCurrentUser() {
        User user = SecurityUtils.getUser();
        assert user != null;
        return UserConverter.from(user);
    }
}
