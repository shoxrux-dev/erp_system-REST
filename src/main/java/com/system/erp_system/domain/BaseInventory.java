package com.system.erp_system.domain;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseInventory extends BaseDomain {
    @PrimaryKey
    UUID id;
    String name;
}
