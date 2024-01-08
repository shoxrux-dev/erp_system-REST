package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.SalesConverter;
import com.system.erp_system.dto.sales.SalesResponseDto;
import com.system.erp_system.service.CompanyService;
import com.system.erp_system.service.ProductService;
import com.system.erp_system.service.SalesService;
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
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
@SecurityRequirement(name = SwaggerConfig.BEARER)
@EnableMethodSecurity
public class SalesController {
    private final SalesService salesService;
    private final ProductService productService;
    private final CompanyService companyService;

    @GetMapping
    @Operation(summary = "Get all sales")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SalesResponseDto> getAll(){
        return SalesConverter.from(
                salesService.getAll(),
                productService.getAll(),
                companyService.getAll()
        );
    }

}
