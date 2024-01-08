package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.OutgoingProductConverter;
import com.system.erp_system.domain.OutgoingProduct;
import com.system.erp_system.dto.outgoing_product.OutgoingProductCreateRequestDto;
import com.system.erp_system.dto.outgoing_product.OutgoingProductResponseDto;
import com.system.erp_system.dto.outgoing_product.OutgoingProductUpdateRequestDto;
import com.system.erp_system.service.CompanyService;
import com.system.erp_system.service.InventoryService;
import com.system.erp_system.service.OutgoingProductService;
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
@RequestMapping("/api/v1/outgoing-product")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
public class OutgoingProductController {
    private final OutgoingProductService outgoingProductService;
    private final ProductService productService;
    private final CompanyService companyService;
    private final InventoryService inventoryService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new outgoing product")
    @PreAuthorize("hasRole('ADMIN')")
    public OutgoingProductResponseDto create(@RequestBody @Validated OutgoingProductCreateRequestDto outgoingProductCreateRequestDto){
        OutgoingProduct outgoingProduct = OutgoingProductConverter.convertToDomain(outgoingProductCreateRequestDto);
        OutgoingProduct outgoingProduct1 = outgoingProductService.create(outgoingProduct);

        return OutgoingProductConverter.from(
                outgoingProduct1,
                productService.get(outgoingProduct1.getProductId()),
                companyService.get(outgoingProduct1.getCompanyId()),
                inventoryService.get(outgoingProduct1.getInventoryId())
        );
    }

    @GetMapping()
    @Operation(summary = "Get all the outgoing product")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OutgoingProductResponseDto> getAll(){
        return OutgoingProductConverter.from(
                outgoingProductService.getAll(),
                productService.getAll(),
                companyService.getAll(),
                inventoryService.getAll()
        );
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update the outgoing product by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public OutgoingProductResponseDto update(
            @PathVariable UUID id,
            @RequestBody @Validated OutgoingProductUpdateRequestDto outgoingProductUpdateRequestDto
    ){
        OutgoingProduct outgoingProduct = OutgoingProductConverter.convertToDomain(outgoingProductUpdateRequestDto);
        OutgoingProduct update = outgoingProductService.update(id, outgoingProduct);
        return OutgoingProductConverter.from(
                update,
                productService.get(update.getProductId()),
                companyService.get(update.getCompanyId()),
                inventoryService.get(update.getInventoryId())
        );
    }
}
