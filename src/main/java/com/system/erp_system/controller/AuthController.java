package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.config.token.JwtService;
import com.system.erp_system.convertor.UserConverter;
import com.system.erp_system.domain.User;
import com.system.erp_system.dto.user.UserCreateRequestDto;
import com.system.erp_system.dto.user.UserLoginRequestDto;
import com.system.erp_system.dto.user.UserResponseDto;
import com.system.erp_system.service.UserService;
import com.system.erp_system.util.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registration a new user")
    public UserResponseDto create(@RequestBody @Validated UserCreateRequestDto userCreateRequestDto) {
        User user = UserConverter.convertToDomain(userCreateRequestDto);
        UserResponseDto userResponseDto = UserConverter.from(userService.create(user));
        userResponseDto.setToken(jwtService.generateToken(user));
        return userResponseDto;
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public UserResponseDto login(@RequestBody @Validated UserLoginRequestDto userLoginRequestDto) {
        User user = UserConverter.convertToDomain(userLoginRequestDto);
        UserResponseDto userResponseDto = UserConverter.from(userService.login(user));
        userResponseDto.setToken(jwtService.generateToken(user));
        return userResponseDto;
    }

}
