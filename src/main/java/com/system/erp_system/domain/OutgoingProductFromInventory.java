package com.system.erp_system.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("outgoing_product_from_inventory")
public class OutgoingProductFromInventory extends BaseDomain implements Comparable<OutgoingProductFromInventory> {
    @PrimaryKey
    UUID id;
    @Column("product_id")
    UUID productId;
    Integer count;
    @Column("inventory_id")
    UUID inventoryId;

    @Override
    public int compareTo(@NotNull OutgoingProductFromInventory o) {
        return o.getCreatedAt().compareTo(this.getCreatedAt());
    }
}
