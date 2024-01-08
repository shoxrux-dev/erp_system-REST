package com.system.erp_system.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("sales")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sales extends BaseDomain implements Comparable<Sales> {
    @PrimaryKey
    UUID id;
    @Column("product_id")
    UUID productId;
    @Column("company_id")
    UUID companyId;
    Integer count;
    @Column("revenue")
    BigDecimal revenue;

    @Override
    public int compareTo(@NotNull Sales o) {
        return o.getCreatedAt().compareTo(this.getCreatedAt());
    }

}
