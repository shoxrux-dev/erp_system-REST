package com.system.erp_system.dto.incoming_product_to_inventory;

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
public class IncomingProductToInventoryResponseDto extends BaseResponseDto {
    Integer count;
    UUID productId;
    String productName;
    UUID inventoryId;
    String inventoryName;
}
