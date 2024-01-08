package com.system.erp_system.dto.debt;

import com.system.erp_system.constant.DebtStatusEnum;
import com.system.erp_system.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DebtResponseDto extends BaseResponseDto {
    UUID companyId;
    String companyName;
    BigDecimal amount;
    DebtStatusEnum status;
}
