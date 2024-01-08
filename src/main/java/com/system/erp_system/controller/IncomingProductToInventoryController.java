package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.IncomingProductToInventoryConverter;
import com.system.erp_system.dto.incoming_product_to_inventory.IncomingProductToInventoryResponseDto;
import com.system.erp_system.service.IncomingProductToInventoryService;
import com.system.erp_system.service.InventoryService;
import com.system.erp_system.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/incoming-product-to-inventory")
@RequiredArgsConstructor
@SecurityRequirement(name = SwaggerConfig.BEARER)
@EnableMethodSecurity
public class IncomingProductToInventoryController {
    private final IncomingProductToInventoryService incomingProductToInventoryService;
    private final InventoryService inventoryService;
    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get all the incoming product to inventory")
    @PreAuthorize("hasRole('ADMIN')")
    public List<IncomingProductToInventoryResponseDto> getAll() {
        return IncomingProductToInventoryConverter.from(
                incomingProductToInventoryService.getAll(),
                inventoryService.getAll(),
                productService.getAll()
        );
    }

}
