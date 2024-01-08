package com.system.erp_system.dto.account;

import com.system.erp_system.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponseDto extends BaseResponseDto {
    BigDecimal balance;
}
