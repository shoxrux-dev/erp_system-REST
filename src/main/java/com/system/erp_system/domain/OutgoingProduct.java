package com.system.erp_system.domain;

import com.system.erp_system.constant.DebtStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Table("outgoing_product")
public class OutgoingProduct extends BaseProductMovement implements Comparable<OutgoingProduct> {
    @Enumerated(EnumType.STRING)
    DebtStatusEnum status;

    @Override
    public int compareTo(@NotNull OutgoingProduct o) {
        return o.getCreatedAt().compareTo(this.getCreatedAt());
    }
}
