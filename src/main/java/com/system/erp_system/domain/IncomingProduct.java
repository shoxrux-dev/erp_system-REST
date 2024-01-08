package com.system.erp_system.domain;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("incoming_product")
public class IncomingProduct extends BaseProductMovement implements Comparable<IncomingProduct> {
    @Override
    public int compareTo(@NotNull IncomingProduct o) {
        return o.getCreatedAt().compareTo(this.getCreatedAt());
    }
}
