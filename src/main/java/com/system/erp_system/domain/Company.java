package com.system.erp_system.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("company")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company extends BaseDomain {
    @PrimaryKey
    UUID id;
    String name;
}
