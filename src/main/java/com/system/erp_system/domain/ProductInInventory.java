package com.system.erp_system.domain;

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
@Table("product_in_inventory")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductInInventory extends BaseDomain{
    @PrimaryKey
    UUID id;
    @Column("product_id")
    UUID productId;
    @Column("inventory_id")
    UUID inventoryId;
    Integer count;
}
