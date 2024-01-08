package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.OutgoingProductFromInventoryConverter;
import com.system.erp_system.dto.outgoing_product_from_inventory.OutgoingProductFromInventoryResponseDto;
import com.system.erp_system.service.InventoryService;
import com.system.erp_system.service.OutgoingProductFromInventoryService;
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
@RequestMapping("/api/v1/outgoing-product-from-inventory")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
public class OutgoingProductFromInventoryController {
    private final OutgoingProductFromInventoryService outgoingProductFromInventoryService;
    private final ProductService productService;
    private final InventoryService inventoryService;

    @GetMapping()
    @Operation(summary = "Get all the outgoing product from inventory")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OutgoingProductFromInventoryResponseDto> getAll(){
        return OutgoingProductFromInventoryConverter.from(
                outgoingProductFromInventoryService.getAll(),
                productService.getAll(),
                inventoryService.getAll()
                );
    }

}
