package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.InventoryConverter;
import com.system.erp_system.domain.Inventory;
import com.system.erp_system.dto.inventory.InventoryCreateRequestDto;
import com.system.erp_system.dto.inventory.InventoryResponseDto;
import com.system.erp_system.dto.inventory.InventoryUpdateRequestDto;
import com.system.erp_system.service.InventoryService;
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
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new inventory")
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponseDto create(@RequestBody @Validated InventoryCreateRequestDto inventoryCreateRequestDto){
        Inventory inventory = InventoryConverter.convertToDomain(inventoryCreateRequestDto);
        return InventoryConverter.from(inventoryService.create(inventory));
    }

    @GetMapping
    @Operation(summary = "Get all inventories")
    @PreAuthorize("hasRole('ADMIN')")
    public List<InventoryResponseDto> getAll(){
        return InventoryConverter.from(inventoryService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the inventory by id")
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponseDto get(@PathVariable UUID id){
        return InventoryConverter.from(inventoryService.get(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the inventory by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponseDto update(
            @PathVariable UUID id,
            @RequestBody @Validated InventoryUpdateRequestDto inventoryUpdateRequestDto
    ){
        Inventory inventory = InventoryConverter.convertToDomain(inventoryUpdateRequestDto);
        return InventoryConverter.from(inventoryService.update(id, inventory));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the inventory by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        inventoryService.delete(id);
    }

}
