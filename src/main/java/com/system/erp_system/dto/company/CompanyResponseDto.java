package com.system.erp_system.dto.company;

import com.system.erp_system.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyResponseDto extends BaseResponseDto {
    String name;
}
