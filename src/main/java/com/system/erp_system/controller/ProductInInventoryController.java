package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.ProductInInventoryConverter;
import com.system.erp_system.dto.product_in_inventory.ProductInInventoryResponseDto;
import com.system.erp_system.service.InventoryService;
import com.system.erp_system.service.ProductInInventoryService;
import com.system.erp_system.service.ProductService;
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
@RequestMapping("/api/v1/product-in-inventory")
@RequiredArgsConstructor
@SecurityRequirement(name = SwaggerConfig.BEARER)
@EnableMethodSecurity
public class ProductInInventoryController {
    private final ProductInInventoryService productInInventoryService;
    private final InventoryService inventoryService;
    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get all the incoming product to inventory")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductInInventoryResponseDto> getAll() {
        return ProductInInventoryConverter.from(
                productInInventoryService.getAll(),
                inventoryService.getAll(),
                productService.getAll()
        );
    }

}
