package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.DebtConverter;
import com.system.erp_system.dto.debt.DebtResponseDto;
import com.system.erp_system.service.CompanyService;
import com.system.erp_system.service.DebtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/debt")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
public class DebtController {
    private final DebtService debtService;
    private final CompanyService companyService;

    @GetMapping
    @Operation(summary = "Get all the debt")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DebtResponseDto> getAll() {
        return DebtConverter.from(debtService.getAll(), companyService.getAll());
    }

}
