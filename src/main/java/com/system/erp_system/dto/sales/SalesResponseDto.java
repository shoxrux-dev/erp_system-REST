package com.system.erp_system.dto.sales;

import com.system.erp_system.dto.BaseResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalesResponseDto extends BaseResponseDto {
    UUID productId;
    String productName;
    UUID companyId;
    String companyName;
    Integer count;
    BigDecimal revenue;
}
