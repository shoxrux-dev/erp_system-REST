package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.ProductConvertor;
import com.system.erp_system.domain.Product;
import com.system.erp_system.dto.product.ProductCreateRequestDto;
import com.system.erp_system.dto.product.ProductResponseDto;
import com.system.erp_system.dto.product.ProductUpdateRequestDto;
import com.system.erp_system.service.CategoryService;
import com.system.erp_system.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponseDto create(@ModelAttribute ProductCreateRequestDto productCreateRequestDto){
        Product product = ProductConvertor.convertToDomain(productCreateRequestDto);
        return ProductConvertor.from(
                productService.create(product, productCreateRequestDto.getImage()),
                categoryService.get(productCreateRequestDto.getCategoryId())
        );
    }

    @GetMapping
    @Operation(summary = "Get all products")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductResponseDto> getAll(){ //List<ProductResponseDto>
        return ProductConvertor.from(
                productService.getAll(),
                categoryService.getAll()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the product by id")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ProductResponseDto get(@PathVariable UUID id){
        Product product = productService.get(id);
        return ProductConvertor.from(product, categoryService.get(product.getCategoryId()));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update the company by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto update(
            @PathVariable UUID id,
            @ModelAttribute ProductUpdateRequestDto productUpdateRequestDto
            ){
        Product product = ProductConvertor.convertToDomain(productUpdateRequestDto);
        Product update = productService.update(id, product, productUpdateRequestDto.getImage());
        return ProductConvertor.from(update, categoryService.get(update.getCategoryId()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the product by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        productService.delete(id);
    }

}
