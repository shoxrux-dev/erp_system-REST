package com.system.erp_system.dto.product_in_inventory;

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
public class ProductInInventoryResponseDto extends BaseResponseDto {
    Integer count;
    UUID inventoryId;
    String inventoryName;
    UUID productId;
    String productName;
}
