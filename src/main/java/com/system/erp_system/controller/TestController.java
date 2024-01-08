package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
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
@RequestMapping("/api/v1/test")
@SecurityRequirement(name = SwaggerConfig.BEARER)
@RequiredArgsConstructor
@EnableMethodSecurity
public class TestController {
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getHello(){
        return "Hello world";
    }
}
