package com.system.erp_system.repository;

import com.system.erp_system.domain.Sales;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalesRepository extends CassandraRepository<Sales, UUID> {
    @Query(allowFiltering = true)
    Optional<Sales> findByUpdatedAtBetween(Instant start, Instant end);
}
