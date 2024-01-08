package com.system.erp_system.convertor;

import com.system.erp_system.domain.Category;
import com.system.erp_system.dto.category.BaseCategoryRequestDto;
import com.system.erp_system.dto.category.CategoryResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CategoryConverter {
    public <T extends BaseCategoryRequestDto> Category convertToDomain(T request) {
        return Category.builder()
                .name(request.getName())
                .build();
    }

    public CategoryResponseDto from(Category category){
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public List<CategoryResponseDto> from(List<Category> categories) {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();

        categories.forEach((category -> categoryResponseDtos.add(
                CategoryResponseDto.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .createdAt(category.getCreatedAt())
                        .updatedAt(category.getUpdatedAt())
                        .build()
        )));

        return categoryResponseDtos;
    }

}
