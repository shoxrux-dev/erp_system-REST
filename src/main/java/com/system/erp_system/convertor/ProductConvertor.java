package com.system.erp_system.convertor;

import com.system.erp_system.constant.FilePathConstant;
import com.system.erp_system.domain.Category;
import com.system.erp_system.domain.Product;
import com.system.erp_system.dto.product.BaseProductRequestDto;
import com.system.erp_system.dto.product.ProductResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ProductConvertor {
    public <T extends BaseProductRequestDto> Product convertToDomain(T request) {
        return Product.builder()
                .name(request.getName())
                .categoryId(request.getCategoryId())
                .build();
    }

    public ProductResponseDto from(Product product, Category category) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .image(FilePathConstant.GET_FILE_PATH+product.getImage())
                .categoryId(category.getId())
                .categoryName(category.getName())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public List<ProductResponseDto> from(List<Product> products, List<Category> categories) {
        ArrayList<ProductResponseDto> productResponseDtos = new ArrayList<>();

        products.forEach((product -> categories.forEach(category -> {
            if(category.getId().equals(product.getCategoryId())) {
                productResponseDtos.add(
                        ProductResponseDto.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .image(FilePathConstant.GET_FILE_PATH+product.getImage())
                                .categoryId(product.getCategoryId())
                                .categoryName(category.getName())
                                .createdAt(product.getCreatedAt())
                                .updatedAt(product.getUpdatedAt())
                                .build()
                );
            }
        })));

        return productResponseDtos;
    }

}
