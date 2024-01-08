package com.system.erp_system.dto.incoming_product;

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
public class BaseIncomingProductRequestDto {
    @NotNull UUID productId;
    @NotNull UUID companyId;
    @NotNull Integer count;
    @NotNull BigDecimal price;
    @NotNull UUID inventoryId;
}