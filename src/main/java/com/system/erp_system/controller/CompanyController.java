package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.CompanyConverter;
import com.system.erp_system.domain.Company;
import com.system.erp_system.dto.company.CompanyCreateRequestDto;
import com.system.erp_system.dto.company.CompanyResponseDto;
import com.system.erp_system.dto.company.CompanyUpdateRequestDto;
import com.system.erp_system.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new company")
    @PreAuthorize("hasRole('ADMIN')")
    public CompanyResponseDto create(@RequestBody @Validated CompanyCreateRequestDto companyCreateRequestDto){
        Company company = CompanyConverter.convertToDomain(companyCreateRequestDto);
        return CompanyConverter.from(companyService.create(company));
    }

    @GetMapping
    @Operation(summary = "Get all companies")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CompanyResponseDto> getAll(){
        return CompanyConverter.from(companyService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the company by id")
    @PreAuthorize("hasRole('ADMIN')")
    public CompanyResponseDto get(@PathVariable UUID id){
        return CompanyConverter.from(companyService.get(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the company by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponseDto update(
            @PathVariable UUID id,
            @RequestBody @Validated CompanyUpdateRequestDto companyUpdateRequestDto
    ){
        Company company = CompanyConverter.convertToDomain(companyUpdateRequestDto);
        return CompanyConverter.from(companyService.update(id, company));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the company by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        companyService.delete(id);
    }

}
