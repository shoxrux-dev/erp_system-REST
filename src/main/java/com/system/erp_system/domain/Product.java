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
@Table("product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseDomain {
    @PrimaryKey
    UUID id;
    String name;
    String image;
    @Column("category_id")
    UUID categoryId;
}
