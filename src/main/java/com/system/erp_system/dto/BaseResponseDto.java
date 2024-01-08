package com.system.erp_system.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponseDto {
    UUID id;
    Instant createdAt;
    Instant updatedAt;
}
