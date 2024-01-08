package com.system.erp_system.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseProductMovement extends BaseDomain {
    @PrimaryKey
    UUID id;
    @Column("product_id")
    UUID productId;
    @Column("company_id")
    UUID companyId;
    int count;
    BigDecimal price;
    @Column("inventory_id")
    UUID inventoryId;
}
