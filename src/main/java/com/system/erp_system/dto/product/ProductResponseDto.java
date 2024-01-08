package com.system.erp_system.dto.product;

import com.system.erp_system.dto.BaseResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto extends BaseResponseDto {
    String name;
    String image;
    UUID categoryId;
    String categoryName;
}
