package com.system.erp_system.dto.outgoing_product_from_inventory;

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
public class OutgoingProductFromInventoryResponseDto extends BaseResponseDto {
    Integer count;
    UUID productId;
    String productName;
    UUID inventoryId;
    String inventoryName;
}
