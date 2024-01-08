package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.IncomingAndOutgoingToAccountConverter;
import com.system.erp_system.dto.incoming_and_outgoing_to_account.IncomingAndOutgoingToAccountResponseDto;
import com.system.erp_system.service.CompanyService;
import com.system.erp_system.service.IncomingAndOutgoingToAccountService;
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
@RequestMapping("/api/v1/incoming-and-outgoing-to-account")
@RequiredArgsConstructor
@SecurityRequirement(name = SwaggerConfig.BEARER)
@EnableMethodSecurity
public class IncomingAndOutgoingToAccountController {
    private final IncomingAndOutgoingToAccountService incomingAndOutgoingToAccountService;
    private final CompanyService companyService;

    @GetMapping
    @Operation(summary = "Get all the incoming and outgoing to account")
    @PreAuthorize("hasRole('ADMIN')")
    public List<IncomingAndOutgoingToAccountResponseDto> getAll() {
        return IncomingAndOutgoingToAccountConverter.from(
                incomingAndOutgoingToAccountService.getAll(),
                companyService.getAll()
        );
    }

}
