package com.system.erp_system.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("incoming_product_to_inventory")
public class IncomingProductToInventory extends BaseDomain implements Comparable<IncomingProductToInventory> {
    @PrimaryKey
    UUID id;
    @Column("product_id")
    UUID productId;
    Integer count;
    @Column("inventory_id")
    UUID inventoryId;

    @Override
    public int compareTo(@NotNull IncomingProductToInventory o) {
        return o.getCreatedAt().compareTo(this.getCreatedAt());
    }
}
