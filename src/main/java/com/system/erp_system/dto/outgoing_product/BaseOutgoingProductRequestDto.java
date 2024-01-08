package com.system.erp_system.dto.outgoing_product;

import com.system.erp_system.constant.DebtStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseOutgoingProductRequestDto {
    @NotNull UUID productId;
    @NotNull UUID companyId;
    @NotNull Integer count;
    @NotNull BigDecimal price;
    @NotNull UUID inventoryId;
    @NotNull DebtStatusEnum status;
}