package com.system.erp_system.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseDomain {
    @Column("created_at")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    Instant createdAt;
    @Column("updated_at")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    Instant updatedAt;
}
