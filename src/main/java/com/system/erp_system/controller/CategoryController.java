package com.system.erp_system.controller;

import com.system.erp_system.config.SwaggerConfig;
import com.system.erp_system.convertor.CategoryConverter;
import com.system.erp_system.domain.Category;
import com.system.erp_system.dto.category.CategoryCreateRequestDto;
import com.system.erp_system.dto.category.CategoryResponseDto;
import com.system.erp_system.dto.category.CategoryUpdateRequestDto;
import com.system.erp_system.service.CategoryService;
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
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@SecurityRequirement(name = SwaggerConfig.BEARER)
@EnableMethodSecurity
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new category")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponseDto create(@RequestBody @Validated CategoryCreateRequestDto categoryCreateRequestDto){
        Category category = CategoryConverter.convertToDomain(categoryCreateRequestDto);
        return CategoryConverter.from(categoryService.create(category));
    }

    @GetMapping
    @Operation(summary = "Get all categories")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategoryResponseDto> getAll(){
        return CategoryConverter.from(categoryService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the category by id")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponseDto get(@PathVariable UUID id){
        return CategoryConverter.from(categoryService.get(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the category by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDto update(
            @PathVariable UUID id,
            @RequestBody @Validated CategoryUpdateRequestDto categoryUpdateRequestDto
    ){
        Category category = CategoryConverter.convertToDomain(categoryUpdateRequestDto);
        return CategoryConverter.from(categoryService.update(id, category));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the category by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        categoryService.delete(id);
    }

}
