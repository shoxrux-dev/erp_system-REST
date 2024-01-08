package com.system.erp_system.domain;

import com.system.erp_system.constant.DebtStatusEnum;
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
@Table("debt")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Debt extends BaseDomain implements Comparable<Debt> {
    @PrimaryKey
    UUID id;
    @Column("company_id")
    UUID companyId;
    BigDecimal amount;
    DebtStatusEnum status;

    @Override
    public int compareTo(@NotNull Debt o) {
        return o.getCreatedAt().compareTo(this.getCreatedAt());
    }

}
