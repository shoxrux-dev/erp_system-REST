package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.AccountConverter;
import com.system.erp_system.domain.Account;
import com.system.erp_system.dto.account.AccountCreateRequestDto;
import com.system.erp_system.dto.account.AccountResponseDto;
import com.system.erp_system.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@SecurityRequirement(name = SwaggerConfig.BEARER)
@EnableMethodSecurity
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new account")
    @PreAuthorize("hasRole('ADMIN')")
    public AccountResponseDto create(@RequestBody @Validated AccountCreateRequestDto accountCreateRequestDto){
        Account account = AccountConverter.converterToDomain(accountCreateRequestDto);
        return AccountConverter.from(accountService.create(account));
    }

    @GetMapping
    @Operation(summary = "Get the account")
    @PreAuthorize("hasRole('ADMIN')")
    public AccountResponseDto get(){
        return AccountConverter.from(accountService.get());
    }

}
