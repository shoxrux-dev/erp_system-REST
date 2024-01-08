package com.system.erp_system.domain;

import com.system.erp_system.constant.AccountStatusEnum;
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
@Table("incoming_and_outgoing_to_account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IncomingAndOutgoingToAccount extends BaseDomain implements Comparable<IncomingAndOutgoingToAccount> {
    @PrimaryKey
    UUID id;
    @Column("company_id")
    UUID companyId;
    BigDecimal price;
    AccountStatusEnum status;

    @Override
    public int compareTo(@NotNull IncomingAndOutgoingToAccount o) {
        return o.getCreatedAt().compareTo(this.getCreatedAt());
    }

}
