package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.IncomingProductConverter;
import com.system.erp_system.domain.IncomingProduct;
import com.system.erp_system.dto.incoming_product.IncomingProductCreateRequestDto;
import com.system.erp_system.dto.incoming_product.IncomingProductResponseDto;
import com.system.erp_system.dto.incoming_product.IncomingProductUpdateRequestDto;
import com.system.erp_system.service.CompanyService;
import com.system.erp_system.service.IncomingProductService;
import com.system.erp_system.service.InventoryService;
import com.system.erp_system.service.ProductService;
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
@RequestMapping("/api/v1/incoming-product")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
public class IncomingProductController {
    private final IncomingProductService incomingProductService;
    private final ProductService productService;
    private final CompanyService companyService;
    private final InventoryService inventoryService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new incoming product")
    @PreAuthorize("hasRole('ADMIN')")
    public IncomingProductResponseDto create(@RequestBody @Validated IncomingProductCreateRequestDto incomingProductCreateRequestDto){
        IncomingProduct incomingProduct = IncomingProductConverter.convertToDomain(incomingProductCreateRequestDto);
        IncomingProduct incomingProduct1 = incomingProductService.create(incomingProduct);
        return IncomingProductConverter.from(
                incomingProduct1,
                productService.get(incomingProduct1.getProductId()),
                companyService.get(incomingProduct1.getCompanyId()),
                inventoryService.get(incomingProduct1.getInventoryId())
        );
    }

    @GetMapping()
    @Operation(summary = "Get all the incoming product")
    @PreAuthorize("hasRole('ADMIN')")
    public List<IncomingProductResponseDto> getAll(){
        return IncomingProductConverter.from(
                incomingProductService.getAll(),
                productService.getAll(),
                companyService.getAll(),
                inventoryService.getAll()
        );
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update the incoming product by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public IncomingProductResponseDto update(
            @PathVariable UUID id,
            @RequestBody @Validated IncomingProductUpdateRequestDto incomingProductUpdateRequestDto
    ){
        IncomingProduct incomingProduct = IncomingProductConverter.convertToDomain(incomingProductUpdateRequestDto);
        IncomingProduct update = incomingProductService.update(id, incomingProduct);
        return IncomingProductConverter.from(
                update,
                productService.get(update.getProductId()),
                companyService.get(update.getCompanyId()),
                inventoryService.get(update.getInventoryId())
        );
    }

}