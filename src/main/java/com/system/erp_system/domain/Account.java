package com.system.erp_system.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends BaseDomain {
    @PrimaryKey
    UUID id;
    BigDecimal balance;
}
