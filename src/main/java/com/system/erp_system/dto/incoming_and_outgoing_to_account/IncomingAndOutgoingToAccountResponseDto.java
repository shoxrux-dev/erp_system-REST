package com.system.erp_system.dto.incoming_and_outgoing_to_account;

import com.system.erp_system.constant.AccountStatusEnum;
import com.system.erp_system.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IncomingAndOutgoingToAccountResponseDto extends BaseResponseDto {
    UUID companyId;
    String companyName;
    BigDecimal price;
    AccountStatusEnum status;
}
